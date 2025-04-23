package com.menuplus.backend.user.service;

import com.menuplus.backend.library.common.AuthorizationService;
import com.menuplus.backend.library.enumeration.GeneralStatus;
import com.menuplus.backend.library.exception.ApiException;
import com.menuplus.backend.library.security.JwtTokenProvider;
import com.menuplus.backend.library.security.UserClaims;
import com.menuplus.backend.library.security.UserDetailsCustom;
import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.library.util.PersistentUtil;
import com.menuplus.backend.library.util.RandomUtil;
import com.menuplus.backend.user.api.dto.*;
import com.menuplus.backend.user.api.mapper.UserMapper;
import com.menuplus.backend.user.api.service.UserRoleService;
import com.menuplus.backend.user.api.service.UserService;
import com.menuplus.backend.user.common.ApiUserMessage;
import com.menuplus.backend.user.model.*;
import com.menuplus.backend.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserRoleService userRoleService;

  @Autowired
  @Lazy
  PasswordEncoder passwordEncoder;

  @Autowired
  @Lazy
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  @Lazy
  private AuthorizationService authorizationService;

  @Autowired
  private PersistentUtil persistentUtil;

  @Transactional
  @Override
  public UserDtoResponse create(UserCreateDto createDto) {
    User existedUser = userRepository.findByEmail(createDto.getEmail());
    if (existedUser != null) {
      throw new ApiException(ApiUserMessage.ACCOUNT_EXISTED);
    }

    User user = UserMapper.createUserFromRegisterRequest(createDto);
    String password = createDto.getPassword();
    if (!StringUtils.hasText(password)) {
      password = RandomUtil.generateCode(10, false);
    }
    user.setPassword(passwordEncoder.encode(password));
    user.setUsername(user.getEmail());
    userRepository.save(user);

    var userRoles = createDto.getUserRoles();
    if (userRoles != null) {
      userRoles.forEach(ur -> ur.setUserId(user.getId()));
      userRoleService.createMany(userRoles);
    }

    persistentUtil.flushAndClear();
    return detail(user.getId());
  }

  @Transactional
  @Override
  public SignInResponse signIn(SignInRequest request) {
    User user = userRepository.findByEmailAndStatusIsNot(
      request.getEmail(),
      GeneralStatus.DELETED
    );
    if (user == null) {
      throw new ApiException(ApiUserMessage.ACCOUNT_NOT_FOUND);
    }
    if (user.getStatus() != GeneralStatus.ACTIVE) {
      throw new ApiException(ApiUserMessage.ACCOUNT_IS_INACTIVE);
    }
    try {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword()
        )
      );
      String token = jwtTokenProvider.generateToken(
        (UserDetailsCustom) authentication.getPrincipal()
      );
      authorizationService.addToken(user.getId(), token);

      SignInResponse signInDto = new SignInResponse();
      signInDto.setToken(token);
      signInDto.setAccount(UserMapper.createResponse(user));
      return signInDto;
    } catch (Exception e) {
      throw new ApiException(ApiUserMessage.ACCOUNT_PASSWORD_MISMATCH);
    }
  }

  @Override
  public SignOutResponse signOut() {
    return new SignOutResponse();
  }

  @Transactional
  @Override
  public UserClaims verifyToken(VerifyTokenRequest request) {
    return jwtTokenProvider.getUserClaimsFromToken(request.getToken());
  }

  @Override
  public void forgetPassword(UserForgetPasswordRequest request) {
    // TODO: Implement this method
  }

  @Override
  public void changePassword(UserChangePasswordRequest request) {
    UserDetailsCustom currentUser = UserDetailsCustom.getCurrentUser();
    User user = userRepository.findById(currentUser.getUserId()).get();

    if (
      !passwordEncoder.matches(request.getPassword().trim(), user.getPassword())
    ) {
      throw new ApiException(ApiUserMessage.PASSWORD_MISMATCH);
    }

    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    userRepository.save(user);
    authorizationService.removeTokenUser(true);
  }

  @Override
  public List<PermissionEvaluateDto> getPermissionEvaluate(Long userId) {
    User user = get(userId);
    List<PermissionEvaluateDto> permissionEvaluateDtos = new ArrayList<>();

    for (UserRole userRole : user.getUserRoles()) {
      Role role = userRole.getRole();
      for (RolePermission rolePermission : role.getRolePermissions()) {
        Permission permission = rolePermission.getPermission();

        PermissionEvaluateDto dto = new PermissionEvaluateDto();
        BeanUtils.copyProperties(permission, dto);
        permissionEvaluateDtos.add(dto);
      }
    }

    return permissionEvaluateDtos;
  }

  @Override
  @Transactional
  public UserDtoResponse update(Long id, UserUpdateDto updateDto) {
    User user = userRepository
      .findById(id)
      .orElseThrow(() -> new ApiException(ApiUserMessage.ACCOUNT_NOT_FOUND));

    MapUtil.copyUpdateProperties(updateDto, user);
    userRepository.save(user);

    var userRoles = updateDto.getUserRoles();
    if (userRoles != null) {
      userRoles.forEach(ur -> ur.setUserId(user.getId()));
      userRoleService.upsert(id, userRoles);
    }

    persistentUtil.flushAndClear();
    return detail(user.getId());
  }

  @Override
  @Transactional
  public UserDtoResponse detail(Long id) {
    User user = get(id);
    return UserMapper.createResponse(user);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    User user = get(id);
    try {
      hardDelete(user);
    } catch (Exception ignore) {
      softDelete(user);
    }
  }

  private User get(Long id) {
    Optional<User> userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) {
      throw new ApiException(ApiUserMessage.ACCOUNT_NOT_FOUND);
    }
    return userOpt.get();
  }

  @Transactional
  public void hardDelete(User user) {
    userRepository.delete(user);
  }

  @Transactional
  public void softDelete(User user) {
    user.setStatus(GeneralStatus.DELETED);
    userRepository.save(user);
  }
}

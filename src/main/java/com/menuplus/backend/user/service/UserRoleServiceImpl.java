package com.menuplus.backend.user.service;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.library.util.MapperHelper;
import com.menuplus.backend.user.api.dto.UserRoleCreateDto;
import com.menuplus.backend.user.api.dto.UserRoleResponseDto;
import com.menuplus.backend.user.api.mapper.UserRoleMapper;
import com.menuplus.backend.user.api.service.UserRoleService;
import com.menuplus.backend.user.model.UserRole;
import com.menuplus.backend.user.repository.UserRoleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

  @Autowired
  private UserRoleRepository userRoleRepository;

  @Transactional
  @Override
  public List<UserRoleResponseDto> createMany(
    List<UserRoleCreateDto> createDtos
  ) {
    if (CollectionUtils.isEmpty(createDtos)) {
      return new ArrayList<>();
    }
    var userRoles = new ArrayList<UserRole>();
    for (UserRoleCreateDto createDto : createDtos) {
      var userRole = UserRoleMapper.createEntity(createDto);
      userRoleRepository.save(userRole);
      userRoles.add(userRole);
    }
    return MapperHelper.map(userRoles, UserRoleMapper::createResponse);
  }

  @Override
  @Transactional
  public List<UserRoleResponseDto> upsert(
    Long userId,
    List<UserRoleCreateDto> upsertDtos
  ) {
    List<Long> upsertIds = upsertDtos
      .stream()
      .map(UserRoleCreateDto::getId)
      .filter(Objects::nonNull)
      .toList();

    List<UserRole> existingUserRoles = userRoleRepository.findByUserId(userId);
    Map<Boolean, List<UserRole>> existingMap = existingUserRoles
      .stream()
      .collect(
        Collectors.partitioningBy(userRole ->
          upsertIds.contains(userRole.getId())
        )
      );
    List<UserRole> updateUserRoles = existingMap.get(true);
    List<UserRole> deleteUserRoles = existingMap.get(false);

    Map<Boolean, List<UserRoleCreateDto>> requestMap = upsertDtos
      .stream()
      .collect(Collectors.partitioningBy(c -> c.getId() != null));
    List<UserRoleCreateDto> createDtos = requestMap.get(false);
    List<UserRoleCreateDto> updateDtos = requestMap.get(true);

    List<UserRoleResponseDto> responseDtos = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(deleteUserRoles)) {
      userRoleRepository.deleteAll(deleteUserRoles);
    }
    if (CollectionUtils.isNotEmpty(createDtos)) {
      responseDtos.addAll(createMany(createDtos));
    }
    if (CollectionUtils.isNotEmpty(updateDtos)) {
      responseDtos.addAll(updateMany(updateUserRoles, updateDtos));
    }
    return responseDtos;
  }

  @Override
  @Transactional
  public void deleteByUserId(Long userId) {
    userRoleRepository.deleteByUserId(userId);
  }

  private List<UserRoleResponseDto> updateMany(
    List<UserRole> updateUserScreens,
    List<UserRoleCreateDto> updateDtos
  ) {
    Map<Long, UserRoleCreateDto> updateDtoMap = updateDtos
      .stream()
      .collect(Collectors.toMap(UserRoleCreateDto::getId, Function.identity()));
    for (UserRole updateUserRole : updateUserScreens) {
      var updateDto = updateDtoMap.get(updateUserRole.getId());
      if (updateDto == null) continue;

      MapUtil.copyUpdateProperties(updateDto, updateUserRole);
      userRoleRepository.save(updateUserRole);
    }
    return MapperHelper.map(updateUserScreens, UserRoleMapper::createResponse);
  }
}

package com.menuplus.backend.user.service;

import com.menuplus.backend.library.common.AuthorizationService;
import com.menuplus.backend.library.exception.ApiException;
import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.library.util.PersistentUtil;
import com.menuplus.backend.user.api.dto.*;
import com.menuplus.backend.user.api.mapper.RoleMapper;
import com.menuplus.backend.user.api.service.RolePermissionService;
import com.menuplus.backend.user.api.service.RoleScreenService;
import com.menuplus.backend.user.api.service.RoleService;
import com.menuplus.backend.user.common.ApiUserMessage;
import com.menuplus.backend.user.model.Role;
import com.menuplus.backend.user.model.RolePermission;
import com.menuplus.backend.user.model.RoleScreen;
import com.menuplus.backend.user.model.User;
import com.menuplus.backend.user.repository.RoleRepository;
import com.menuplus.backend.user.repository.UserRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RoleScreenService roleScreenService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private PersistentUtil persistentUtil;

    @Override
    @Transactional
    public RoleResponseDto create(RoleCreateDto createDto) {
        Role role = RoleMapper.createEntity(createDto);
        roleRepository.save(role);
        var rolePermissions = createDto.getRolePermissions();
        if (rolePermissions != null) {
            rolePermissions.forEach(rp -> rp.setRoleId(role.getId()));
            rolePermissionService.createMany(rolePermissions);
        }

        var roleScreens = createDto.getRoleScreens();
        if (roleScreens != null) {
            roleScreens.forEach(rs -> rs.setRoleId(role.getId()));
            roleScreenService.createMany(roleScreens);
        }

        persistentUtil.flushAndClear();
        return detail(role.getId());
    }

    @Override
    @Transactional
    public RoleResponseDto update(Long id, RoleUpdateDto updateDto) {
        Role role = get(id);

        if (isNeedLogout(role, updateDto)) {
            List<User> users = userRepository.findByUserRoles_RoleId(role.getId());
            authorizationService.removeToken(users.stream().map(User::getId).collect(Collectors.toList()));
        }

        MapUtil.copyUpdateProperties(updateDto, role);
        roleRepository.save(role);

        var rolePermissions = updateDto.getRolePermissions();
        if (rolePermissions != null) {
            rolePermissions.forEach(rp -> rp.setRoleId(role.getId()));
        }
        rolePermissionService.upsert(role.getId(), rolePermissions);

        var roleScreens = updateDto.getRoleScreens();
        if (roleScreens != null) {
            roleScreens.forEach(rs -> rs.setRoleId(role.getId()));
        }
        roleScreenService.upsert(role.getId(), roleScreens);

        persistentUtil.flushAndClear();
        return detail(role.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Role role = get(id);

        rolePermissionService.deleteByRoleId(id);
        roleScreenService.deleteByRoleId(id);
        roleRepository.delete(role);
    }

    @Override
    @Transactional
    public RoleResponseDto detail(Long id) {
        Role role = get(id);

        return RoleMapper.createResponse(role);
    }

    @Override
    @Transactional
    public List<RoleResponseDto> list() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDto> result = new ArrayList<>();
        for (Role role : roles) {
            RoleResponseDto dto = RoleMapper.createResponse(role, RoleMapper.ListExcludeFields);
            result.add(dto);
        }
        return result;
    }

    private Role get(Long id) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isEmpty()) {
            throw new ApiException(ApiUserMessage.ROLE_NOT_FOUND);
        }
        return roleOpt.get();
    }

    private boolean isNeedLogout(Role role, RoleUpdateDto updateDto) {
        if (CollectionUtils.size(role.getRolePermissions()) != CollectionUtils.size(updateDto.getRolePermissions())) {
            return true;
        }
        if (CollectionUtils.size(role.getRoleScreens()) != CollectionUtils.size(updateDto.getRoleScreens())) {
            return true;
        }
        if (CollectionUtils.isNotEmpty(updateDto.getRoleScreens())) {
            List<Long> currentScreens = role.getRoleScreens().stream().map(RoleScreen::getScreenId).collect(Collectors.toList());
            List<Long> updateScreens = updateDto.getRoleScreens().stream().map(RoleScreenCreateDto::getScreenId).collect(Collectors.toList());
            if (!CollectionUtils.isEqualCollection(currentScreens, updateScreens)) {
                return true;
            }
        }
        if (CollectionUtils.isNotEmpty(updateDto.getRolePermissions())) {
            Map<Long, RolePermission> currentPermissionMap = role.getRolePermissions().stream()
                    .collect(Collectors.toMap(RolePermission::getPermissionId, Function.identity()));
            Map<Long, RolePermissionCreateDto> updatePermissionMap = updateDto.getRolePermissions().stream()
                    .collect(Collectors.toMap(RolePermissionCreateDto::getPermissionId, Function.identity()));
            for (var entry : currentPermissionMap.entrySet()) {
                RolePermission currentRolePermission = entry.getValue();
                RolePermissionCreateDto updateRolePermission = updatePermissionMap.get(entry.getKey());
                if (!Objects.equals(currentRolePermission.getIsLimitedByOwner(), updateRolePermission.getIsLimitedByOwner())) {
                    return true;
                }
                if (!Objects.equals(currentRolePermission.getLimitedIp(), updateRolePermission.getLimitedIp())) {
                    return true;
                }
            }
        }
        return false;
    }

}

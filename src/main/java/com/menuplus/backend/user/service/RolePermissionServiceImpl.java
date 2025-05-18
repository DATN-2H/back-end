package com.menuplus.backend.user.service;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.library.util.MapperHelper;
import com.menuplus.backend.user.api.dto.RolePermissionCreateDto;
import com.menuplus.backend.user.api.dto.RolePermissionResponseDto;
import com.menuplus.backend.user.api.mapper.RolePermissionMapper;
import com.menuplus.backend.user.api.service.RolePermissionService;
import com.menuplus.backend.user.model.RolePermission;
import com.menuplus.backend.user.repository.RolePermissionRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    @Transactional
    public List<RolePermissionResponseDto> createMany(
        List<RolePermissionCreateDto> createDtos
    ) {
        if (CollectionUtils.isEmpty(createDtos)) {
            return new ArrayList<>();
        }
        var rolePermissions = new ArrayList<RolePermission>();
        for (RolePermissionCreateDto createDto : createDtos) {
            var rolePermission = RolePermissionMapper.createEntity(createDto);
            rolePermissionRepository.save(rolePermission);
            rolePermissions.add(rolePermission);
        }
        return MapperHelper.map(
            rolePermissions,
            RolePermissionMapper::createResponse
        );
    }

    @Override
    @Transactional
    public List<RolePermissionResponseDto> upsert(
        Long roleId,
        List<RolePermissionCreateDto> upsertDtos
    ) {
        List<Long> upsertPermissionIds = upsertDtos
            .stream()
            .map(RolePermissionCreateDto::getPermissionId)
            .toList();

        List<RolePermission> existingRolePermissions =
            rolePermissionRepository.findByRoleId(roleId);
        List<Long> existingPermissionIds = existingRolePermissions
            .stream()
            .map(RolePermission::getPermissionId)
            .toList();

        Map<Boolean, List<RolePermission>> existingMap = existingRolePermissions
            .stream()
            .collect(
                Collectors.partitioningBy(rolePermission ->
                    upsertPermissionIds.contains(
                        rolePermission.getPermissionId()
                    )
                )
            );
        List<RolePermission> updateRolePermissions = existingMap.get(true);
        List<RolePermission> deleteRolePermissions = existingMap.get(false);

        Map<Boolean, List<RolePermissionCreateDto>> requestMap = upsertDtos
            .stream()
            .collect(
                Collectors.partitioningBy(c ->
                    existingPermissionIds.contains(c.getPermissionId())
                )
            );
        List<RolePermissionCreateDto> createDtos = requestMap.get(false);
        List<RolePermissionCreateDto> updateDtos = requestMap.get(true);

        System.out.printf(
            "RolePermissionService: existing permissionIds: %s",
            Arrays.toString(
                Arrays.stream(existingPermissionIds.toArray())
                    .sorted()
                    .toArray()
            )
        );

        System.out.printf(
            "RolePermissionService: update permissionIds: %s",
            Arrays.toString(
                Arrays.stream(upsertPermissionIds.toArray()).sorted().toArray()
            )
        );

        List<RolePermissionResponseDto> responseDtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(deleteRolePermissions)) {
            rolePermissionRepository.deleteAll(deleteRolePermissions);
        }
        if (CollectionUtils.isNotEmpty(createDtos)) {
            createDtos.forEach(r -> r.setRoleId(roleId));
            createDtos.forEach(r -> r.setId(null));
            responseDtos.addAll(createMany(createDtos));
        }
        if (CollectionUtils.isNotEmpty(updateDtos)) {
            //updateDtos.forEach(r -> r.setId(null));
            responseDtos.addAll(updateMany(updateRolePermissions, updateDtos));
        }
        return responseDtos;
    }

    @Override
    @Transactional
    public void deleteByRoleId(Long roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    private List<RolePermissionResponseDto> updateMany(
        List<RolePermission> updateRoleScreens,
        List<RolePermissionCreateDto> updateDtos
    ) {
        Map<Long, RolePermissionCreateDto> updateDtoMap = updateDtos
            .stream()
            .collect(
                Collectors.toMap(
                    RolePermissionCreateDto::getPermissionId,
                    Function.identity()
                )
            );
        for (RolePermission updateRolePermission : updateRoleScreens) {
            var updateDto = updateDtoMap.get(
                updateRolePermission.getPermissionId()
            );
            if (updateDto == null) {
                System.out.printf(
                    "RolePermissionService: ignore update permissionId: %d",
                    updateRolePermission.getPermissionId()
                );
                continue;
            }
            MapUtil.copyUpdateProperties(updateDto, updateRolePermission);
            rolePermissionRepository.save(updateRolePermission);
        }
        return MapperHelper.map(
            updateRoleScreens,
            RolePermissionMapper::createResponse
        );
    }
}

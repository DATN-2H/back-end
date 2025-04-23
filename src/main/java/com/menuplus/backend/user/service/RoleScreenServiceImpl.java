package com.menuplus.backend.user.service;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.library.util.MapperHelper;
import com.menuplus.backend.user.api.dto.RoleScreenCreateDto;
import com.menuplus.backend.user.api.dto.RoleScreenResponseDto;
import com.menuplus.backend.user.api.mapper.RoleScreenMapper;
import com.menuplus.backend.user.api.service.RoleScreenService;
import com.menuplus.backend.user.model.RoleScreen;
import com.menuplus.backend.user.repository.RoleScreenRepository;
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
public class RoleScreenServiceImpl implements RoleScreenService {

  @Autowired
  private RoleScreenRepository roleScreenRepository;

  @Override
  @Transactional
  public List<RoleScreenResponseDto> createMany(
    List<RoleScreenCreateDto> createDtos
  ) {
    if (CollectionUtils.isEmpty(createDtos)) {
      return new ArrayList<>();
    }
    var roleScreens = new ArrayList<RoleScreen>();
    for (RoleScreenCreateDto createDto : createDtos) {
      var roleScreen = RoleScreenMapper.createEntity(createDto);
      roleScreenRepository.save(roleScreen);
      roleScreens.add(roleScreen);
    }
    return MapperHelper.map(roleScreens, RoleScreenMapper::createResponse);
  }

  @Override
  @Transactional
  public List<RoleScreenResponseDto> upsert(
    Long roleId,
    List<RoleScreenCreateDto> upsertDtos
  ) {
    List<Long> upsertIds = upsertDtos
      .stream()
      .map(RoleScreenCreateDto::getId)
      .filter(Objects::nonNull)
      .toList();

    List<RoleScreen> existingRoleScreens = roleScreenRepository.findByRoleId(
      roleId
    );
    Map<Boolean, List<RoleScreen>> existingMap = existingRoleScreens
      .stream()
      .collect(
        Collectors.partitioningBy(roleScreen ->
          upsertIds.contains(roleScreen.getId())
        )
      );
    List<RoleScreen> updateRoleScreens = existingMap.get(true);
    List<RoleScreen> deleteRoleScreens = existingMap.get(false);

    Map<Boolean, List<RoleScreenCreateDto>> requestMap = upsertDtos
      .stream()
      .collect(Collectors.partitioningBy(c -> c.getId() != null));
    List<RoleScreenCreateDto> createDtos = requestMap.get(false);
    List<RoleScreenCreateDto> updateDtos = requestMap.get(true);

    List<RoleScreenResponseDto> responseDtos = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(deleteRoleScreens)) {
      roleScreenRepository.deleteAll(deleteRoleScreens);
    }
    if (CollectionUtils.isNotEmpty(createDtos)) {
      responseDtos.addAll(createMany(createDtos));
    }
    if (CollectionUtils.isNotEmpty(updateDtos)) {
      responseDtos.addAll(updateMany(updateRoleScreens, updateDtos));
    }
    return responseDtos;
  }

  @Override
  @Transactional
  public void deleteByRoleId(Long roleId) {
    roleScreenRepository.deleteByRoleId(roleId);
  }

  private List<RoleScreenResponseDto> updateMany(
    List<RoleScreen> updateRoleScreens,
    List<RoleScreenCreateDto> updateDtos
  ) {
    Map<Long, RoleScreenCreateDto> updateDtoMap = updateDtos
      .stream()
      .collect(
        Collectors.toMap(RoleScreenCreateDto::getId, Function.identity())
      );
    for (RoleScreen updateRoleScreen : updateRoleScreens) {
      var updateDto = updateDtoMap.get(updateRoleScreen.getId());
      if (updateDto == null) continue;

      MapUtil.copyUpdateProperties(updateDto, updateRoleScreen);
      roleScreenRepository.save(updateRoleScreen);
    }
    return MapperHelper.map(
      updateRoleScreens,
      RoleScreenMapper::createResponse
    );
  }
}

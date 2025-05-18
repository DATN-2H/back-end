package com.menuplus.backend.user.api.mapper;

import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.user.api.dto.BranchCreateDto;
import com.menuplus.backend.user.api.dto.BranchRespondDto;
import com.menuplus.backend.user.model.*;
import java.util.List;

public class BranchMapper {

    public static BranchRespondDto createResponse(Branch entity) {
        return createResponse(entity, List.of());
    }

    public static BranchRespondDto createResponse(
        Branch entity,
        List<String> excludeFields
    ) {
        BranchRespondDto responseDto = new BranchRespondDto();
        MapUtil.copyResponseProperties(entity, responseDto);

        if (entity.getManager() != null) {
            responseDto.setManager(
                UserMapper.createResponse(
                    entity.getManager(),
                    List.of(User_.BRANCH)
                )
            );
        }

        return responseDto;
    }

    public static Branch createEntity(BranchCreateDto createDto) {
        Branch entity = new Branch();
        MapUtil.copyCreateProperties(createDto, entity);

        return entity;
    }
}

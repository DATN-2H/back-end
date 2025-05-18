package com.menuplus.backend.user.controller;

import com.menuplus.backend.library.common.Response;
import com.menuplus.backend.user.api.dto.RoleCreateDto;
import com.menuplus.backend.user.api.dto.RoleResponseDto;
import com.menuplus.backend.user.api.dto.RoleUpdateDto;
import com.menuplus.backend.user.api.service.RoleService;
import com.menuplus.backend.user.common.ApiUserMessage;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "")
    public Response<RoleResponseDto> create(
        @Valid @RequestBody RoleCreateDto createDto
    ) {
        return Response.success(
            roleService.create(createDto),
            ApiUserMessage.ROLE_CREATE_SUCCESS
        );
    }

    @PutMapping(value = "/{id}")
    public Response<RoleResponseDto> update(
        @PathVariable Long id,
        @Valid @RequestBody RoleUpdateDto updateDto
    ) {
        return Response.success(
            roleService.update(id, updateDto),
            ApiUserMessage.ROLE_UPDATE_SUCCESS
        );
    }

    @DeleteMapping(value = "/{id}")
    public Response<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return Response.success(ApiUserMessage.ROLE_DELETE_SUCCESS);
    }

    @GetMapping(value = "/{id}")
    public Response<RoleResponseDto> detail(@PathVariable Long id) {
        return Response.success(roleService.detail(id));
    }

    @GetMapping(value = "")
    public Response<List<RoleResponseDto>> list() {
        return Response.success(roleService.list());
    }
}

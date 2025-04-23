package com.menuplus.backend.user.controller;

import com.menuplus.backend.library.common.Response;
import com.menuplus.backend.user.api.dto.*;
import com.menuplus.backend.user.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(value = "")
  public Response<UserDtoResponse> create(
    @Valid @RequestBody UserCreateDto dtoCreate
  ) {
    return Response.success(userService.create(dtoCreate));
  }

  @PutMapping(value = "/{id}")
  public Response<UserDtoResponse> update(
    @PathVariable Long id,
    @Valid @RequestBody UserUpdateDto updateDto
  ) {
    return Response.success(userService.update(id, updateDto));
  }

  @GetMapping(value = "/{id}")
  public Response<UserDtoResponse> detail(@PathVariable Long id) {
    return Response.success(userService.detail(id));
  }

  @DeleteMapping(value = "/{id}")
  public Response<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return Response.success();
  }
}

package com.menuplus.backend.user.controller;

import com.menuplus.backend.library.common.PageResponse;
import com.menuplus.backend.library.common.Response;
import com.menuplus.backend.user.api.dto.BranchCreateDto;
import com.menuplus.backend.user.api.dto.BranchRespondDto;
import com.menuplus.backend.user.api.dto.request.BranchListRequest;
import com.menuplus.backend.user.api.service.BranchService;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping(value = "")
    public Response<BranchRespondDto> create(
        @Valid @RequestBody BranchCreateDto dtoCreate
    ) {
        return Response.success(branchService.create(dtoCreate));
    }

    @GetMapping(value = "")
    public Response<PageResponse<BranchRespondDto>> list(
        @ModelAttribute BranchListRequest request
    ) {
        return Response.success(branchService.list(request));
    }

    @PutMapping(value = "/{id}")
    public Response<BranchRespondDto> update(
        @PathVariable Long id,
        @Valid @RequestBody BranchCreateDto dtoCreate
    ) {
        return Response.success(branchService.update(id, dtoCreate));
    }
}

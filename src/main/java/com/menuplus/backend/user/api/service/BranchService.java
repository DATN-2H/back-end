package com.menuplus.backend.user.api.service;

import com.menuplus.backend.library.common.PageResponse;
import com.menuplus.backend.user.api.dto.BranchCreateDto;
import com.menuplus.backend.user.api.dto.BranchRespondDto;
import com.menuplus.backend.user.api.dto.request.BranchListRequest;
import java.util.List;

public interface BranchService {
    BranchRespondDto create(BranchCreateDto dtoCreate);

    PageResponse<BranchRespondDto> list(BranchListRequest request);

    BranchRespondDto update(Long id, BranchCreateDto dtoCreate);
}

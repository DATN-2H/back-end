package com.menuplus.backend.user.repository.custom;

import com.menuplus.backend.user.api.dto.request.BranchListRequest;
import com.menuplus.backend.user.model.Branch;
import org.springframework.data.domain.Page;

public interface BranchRepositoryCustom {
    Page<Branch> list(BranchListRequest request);
}

package com.menuplus.backend.user.service;

import static com.menuplus.backend.user.common.ApiUserMessage.ACCOUNT_NOT_FOUND;
import static com.menuplus.backend.user.common.ApiUserMessage.BRANCH_NOT_FOUND;

import com.menuplus.backend.library.common.PageResponse;
import com.menuplus.backend.library.enumeration.SystemRole;
import com.menuplus.backend.library.exception.ApiException;
import com.menuplus.backend.library.util.MapUtil;
import com.menuplus.backend.user.api.dto.BranchCreateDto;
import com.menuplus.backend.user.api.dto.BranchRespondDto;
import com.menuplus.backend.user.api.dto.request.BranchListRequest;
import com.menuplus.backend.user.api.mapper.BranchMapper;
import com.menuplus.backend.user.api.service.BranchService;
import com.menuplus.backend.user.api.service.UserRoleService;
import com.menuplus.backend.user.model.Branch;
import com.menuplus.backend.user.model.User;
import com.menuplus.backend.user.repository.BranchRepository;
import com.menuplus.backend.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Transactional
    public BranchRespondDto create(BranchCreateDto dtoCreate) {
        Branch branch = BranchMapper.createEntity(dtoCreate);

        branchRepository.save(branch);

        if (dtoCreate.getManagerId() != null) {
            User manager = userRepository
                .findById(dtoCreate.getManagerId())
                .orElseThrow(() -> new ApiException(ACCOUNT_NOT_FOUND));

            manager.setBranch(branch);
            manager.setIsManager(true);
            userRepository.save(manager);
            userRoleService.setRoleForUser(
                dtoCreate.getManagerId(),
                SystemRole.MANAGER
            );

            branch.setManager(manager);
        }
        return BranchMapper.createResponse(branch);
    }

    @Override
    @Transactional
    public PageResponse<BranchRespondDto> list(BranchListRequest request) {
        Page<Branch> branches = branchRepository.list(request);
        return PageResponse.buildPageDtoResponse(
            branches,
            BranchMapper::createResponse
        );
    }

    @Override
    @Transactional
    public BranchRespondDto update(Long id, BranchCreateDto dtoUpdate) {
        Branch branch = branchRepository
            .findById(id)
            .orElseThrow(() -> new ApiException(BRANCH_NOT_FOUND));

        MapUtil.copyUpdateProperties(dtoUpdate, branch);

        if (dtoUpdate.getManagerId() != null) {
            User oldManager = userRepository.findByIsManagerAndBranchId(
                true,
                id
            );
            if (oldManager != null) {
                userRoleService.setRoleForUser(
                    oldManager.getId(),
                    SystemRole.EMPLOYEE
                );
                oldManager.setIsManager(false);
            }

            User manager = userRepository
                .findById(dtoUpdate.getManagerId())
                .orElseThrow(() -> new ApiException(ACCOUNT_NOT_FOUND));

            manager.setBranch(branch);
            manager.setIsManager(true);
            userRepository.save(manager);

            userRoleService.setRoleForUser(
                dtoUpdate.getManagerId(),
                SystemRole.MANAGER
            );
            branch.setManager(manager);
        }
        return BranchMapper.createResponse(branchRepository.save(branch));
    }
}

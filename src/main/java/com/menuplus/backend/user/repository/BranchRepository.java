package com.menuplus.backend.user.repository;

import com.menuplus.backend.user.model.Branch;
import com.menuplus.backend.user.repository.custom.BranchRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository
    extends JpaRepository<Branch, Long>, BranchRepositoryCustom {}

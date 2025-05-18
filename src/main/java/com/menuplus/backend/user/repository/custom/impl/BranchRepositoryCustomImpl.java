package com.menuplus.backend.user.repository.custom.impl;

import com.menuplus.backend.library.util.PageUtil;
import com.menuplus.backend.library.util.QueryUtil;
import com.menuplus.backend.user.api.dto.request.BranchListRequest;
import com.menuplus.backend.user.model.Branch;
import com.menuplus.backend.user.model.User;
import com.menuplus.backend.user.repository.UserRepository;
import com.menuplus.backend.user.repository.custom.BranchRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BranchRepositoryCustomImpl implements BranchRepositoryCustom {

    private final EntityManager entityManager;
    private final QueryUtil queryUtil;
    private final UserRepository userRepository;

    @Autowired
    public BranchRepositoryCustomImpl(
        EntityManager entityManager,
        QueryUtil queryUtil,
        UserRepository userRepository
    ) {
        this.entityManager = entityManager;
        this.queryUtil = queryUtil;
        this.userRepository = userRepository;
    }

    @Override
    public Page<Branch> list(BranchListRequest request) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
        Root<Branch> root = query.from(Branch.class);
        query.select(root).distinct(true);

        List<Predicate> predicates = new ArrayList<>();

        //        Theem ddieu kieen

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        query.orderBy(PageUtil.generateOrder(request, builder, root, "id"));

        TypedQuery<Branch> typedQuery = entityManager.createQuery(query);
        var total = queryUtil.count(builder, query, root);
        Pageable pageable = request.getPageable();

        List<Branch> list = typedQuery
            .setFirstResult((int) pageable.getOffset())
            .setMaxResults(pageable.getPageSize())
            .getResultList();

        list = list
            .stream()
            .map(branch -> {
                User manager = userRepository.findByIsManagerAndBranchId(
                    true,
                    branch.getId()
                );
                branch.setManager(manager);
                return branch;
            })
            .collect(Collectors.toList());

        return (new PageImpl<>(list, pageable, total));
    }
}

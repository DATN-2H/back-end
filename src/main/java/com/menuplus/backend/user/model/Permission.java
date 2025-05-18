package com.menuplus.backend.user.model;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.RequestMethod;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Permission extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String permissionGroup;

    @Enumerated(EnumType.STRING)
    private RequestMethod method;

    private String pattern;
}

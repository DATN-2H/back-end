package com.menuplus.backend.user.model;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.Gender;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
public class User extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String fullName;
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;
    private Boolean isFullRole;
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>();

}

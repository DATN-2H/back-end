package com.menuplus.backend.user.model;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Configuration extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double percentForBooking;
    private Integer timeToCallBot;
    private Double depositForTable;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchId", nullable = false, unique = true)
    private Branch branch;
}

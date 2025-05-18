package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Combo extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(
        mappedBy = "combo",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ComboProduct> comboProducts = new ArrayList<>();
}

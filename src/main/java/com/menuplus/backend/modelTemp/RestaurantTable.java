package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.TableStatus;
import com.menuplus.backend.library.enumeration.TableType;
import com.menuplus.backend.user.model.Branch;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class RestaurantTable extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacity;
    private Integer xPosition;
    private Integer yPosition;

    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    @Enumerated(EnumType.STRING)
    private TableType tableType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchId", nullable = false)
    private Branch branch;
    //    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    //    private List<BookingTableTable> bookingTables = new ArrayList<>();
}

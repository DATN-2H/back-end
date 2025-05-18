package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.BookingStatus;
import com.menuplus.backend.library.enumeration.OrderType;
import com.menuplus.backend.user.model.Branch;
import com.menuplus.backend.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class BookingProduct extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime eatTime;
    private String note;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Enumerated(EnumType.STRING)
    private OrderType bookingType;

    private String deliveryInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchId", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", nullable = false)
    private User customer;

    @OneToMany(
        mappedBy = "bookingProduct",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<BookingProductProduct> products = new ArrayList<>();
}

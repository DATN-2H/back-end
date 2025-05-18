package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.OrderStatus;
import com.menuplus.backend.library.enumeration.OrderType;
import com.menuplus.backend.user.model.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "orders") // tránh dùng từ khóa SQL "order"
public class Order extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus oderStatus;

    private String orderName;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    private BigDecimal depositApplied;
    private BigDecimal totalAmount;
    private String deliveryInfo;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", nullable = false)
    private User customer;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderTable> tables = new ArrayList<>();

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderProduct> products = new ArrayList<>();
}

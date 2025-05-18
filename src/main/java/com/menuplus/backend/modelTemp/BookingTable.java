package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.BookingStatus;
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
public class BookingTable extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;
    private Integer guestCount;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", nullable = false)
    private User customer;

    @OneToMany(
        mappedBy = "bookingTable",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<BookingTableTable> bookingTables = new ArrayList<>();
}

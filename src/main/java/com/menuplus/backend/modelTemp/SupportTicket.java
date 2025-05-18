package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.SupportTicketStatus;
import com.menuplus.backend.library.enumeration.SupportTicketType;
import com.menuplus.backend.user.model.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SupportTicket extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private SupportTicketStatus ticketStatus;

    @Enumerated(EnumType.STRING)
    private SupportTicketType type;

    private Integer rating;
    private BigDecimal amount;

    // Ai tạo ticket (khách hàng)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", nullable = false)
    private User customer;

    // Nhân viên xử lý ticket
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId")
    private User staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingTableId")
    private BookingTable bookingTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingProductId")
    private BookingProduct bookingProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;
}

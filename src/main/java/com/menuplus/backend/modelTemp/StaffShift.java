package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import com.menuplus.backend.library.enumeration.ShiftStatus;
import com.menuplus.backend.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class StaffShift extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private String note;

    @Enumerated(EnumType.STRING)
    private ShiftStatus shiftStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shiftId", nullable = false)
    private Shift shift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId", nullable = false)
    private User staff;
}

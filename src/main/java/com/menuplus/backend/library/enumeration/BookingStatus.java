package com.menuplus.backend.library.enumeration;

public enum BookingStatus {
    BOOKED("Booked"),
    DEPOSIT_PAID("Deposit Paid"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String value;

    BookingStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

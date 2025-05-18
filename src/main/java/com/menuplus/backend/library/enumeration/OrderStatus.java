package com.menuplus.backend.library.enumeration;

public enum OrderStatus {
    PLACED("Order has been placed"),
    PREPARING("Order is being prepared"),
    COMPLETED("Order has been completed"),
    CANCELLED("Order has been cancelled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

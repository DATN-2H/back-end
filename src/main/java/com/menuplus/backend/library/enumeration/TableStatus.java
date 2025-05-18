package com.menuplus.backend.library.enumeration;

public enum TableStatus {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    NEEDS_CLEANING("Needs Cleaning");

    private final String value;

    TableStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

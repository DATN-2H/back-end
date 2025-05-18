package com.menuplus.backend.library.enumeration;

public enum TableType {
    STANDARD("Standard"),
    VIP("VIP");

    private final String value;

    TableType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

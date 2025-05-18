package com.menuplus.backend.library.enumeration;

public enum ShiftStatus {
    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    CONFLICTED("CONFLICTED");

    private final String value;

    ShiftStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

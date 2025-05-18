package com.menuplus.backend.library.enumeration;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other");

    private final String display;

    Gender(String display) {
        this.display = display;
    }

    public String asTitle() {
        return switch (this) {
            case FEMALE -> "Ms.";
            case MALE -> "Mr.";
            default -> "";
        };
    }

    public String asVNTitle() {
        return switch (this) {
            case FEMALE -> "Chị";
            case MALE -> "Anh";
            default -> "";
        };
    }

    public int asMisaGender() {
        return switch (this) {
            case FEMALE -> 0;
            case MALE -> 1;
            default -> 0;
        };
    }

    public String getDisplay() {
        return display;
    }
}

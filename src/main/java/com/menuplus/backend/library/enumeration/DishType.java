package com.menuplus.backend.library.enumeration;

public enum DishType {
    CONSUMABLE("CONSUMABLE"), // Ví dụ: bottle of wine, box of cookies
    STOCKABLE("STOCKABLE"), // Ví dụ: plate of rice, pot of soup
    SERVICE("SERVICE"), // Ví dụ: các dịch vụ khác.
    EXTRA("EXTRA"); // Ví dụ: các món ăn kèm, gia vị

    private final String value;

    DishType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

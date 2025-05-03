package com.menuplus.backend.library.enumeration;

public enum DishType {
  COUNTABLE("Countable"), // Ví dụ: bottle of wine, box of cookies
  UNCOUNTABLE("Uncountable"); // Ví dụ: plate of rice, pot of soup

  private final String value;

  DishType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

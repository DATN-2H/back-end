package com.menuplus.backend.library.enumeration;

public enum OrderType {
  DINE_IN("Dine In"),
  TAKE_AWAY("Take Away"),
  DELIVERY("Delivery");

  private final String value;

  OrderType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

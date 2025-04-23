package com.menuplus.backend.library.enumeration;

public enum SystemRole {
  MANAGER("MANAGER"),
  WAITER("WAITER"),
  HOST("HOST"),
  KITCHEN("KITCHEN"),
  CASHIER("CASHIER"),
  ACCOUNTANT("ACCOUNTANT"),
  EMPLOYEE("EMPLOYEE"),
  CUSTOMER("CUSTOMER"),
  SUPPORT("SUPPORT"),
  SYSTEM_ADMIN("SYSTEM_ADMIN");

  private final String value;

  SystemRole(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

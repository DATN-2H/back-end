package com.menuplus.backend.library.enumeration;

public enum ProductStatus {
  PENDING("Waiting for preparation"),
  PREPARING("Being prepared"),
  COMPLETED("Preparation completed"),
  SERVED("Served to customer"),
  CANCELLED("Cancelled");

  private final String description;

  ProductStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}

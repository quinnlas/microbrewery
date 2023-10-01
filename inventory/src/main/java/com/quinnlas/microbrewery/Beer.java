package com.quinnlas.microbrewery;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Beer {
  private String name;
  private String taste;
  private int quantity;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTaste() {
    return taste;
  }
  public void setTaste(String taste) {
    this.taste = taste;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public Beer(String name, String taste, int quantity) {
    this.name = name;
    this.taste = taste;
    this.quantity = quantity;
  }
}

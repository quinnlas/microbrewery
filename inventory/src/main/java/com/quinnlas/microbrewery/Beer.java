package com.quinnlas.microbrewery;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Beer {
  private String name;
  private String taste;
  private int quantity;
  private double price;

  public double getPrice() {
    return price;
  }
  public void setPrice(double price) {
    this.price = price;
  }
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
  public Beer(String name, String taste, int quantity, double price) {
    this.name = name;
    this.taste = taste;
    this.quantity = quantity;
    this.price = price;
  }
}

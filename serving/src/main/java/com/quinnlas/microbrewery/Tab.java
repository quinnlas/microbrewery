package com.quinnlas.microbrewery;

import java.util.HashMap;

public class Tab {
  HashMap<Beer, Integer> beerQuantities = new HashMap<>();
  double remaining;
  public record Beer(String name, double price){};
  public Tab() {
    this.beerQuantities = new HashMap<Beer, Integer>();
    this.remaining = 0;
  }

  public void addBeer(Beer beer) {
    Integer quantity = this.beerQuantities.get(beer);
    if (quantity == null) quantity = 1;
    else quantity += 1;
    this.beerQuantities.put(beer, quantity);

    this.remaining += beer.price;
  }

  public double makePayment(double amount) {
    this.remaining -= amount;
    return remaining;
  }
}

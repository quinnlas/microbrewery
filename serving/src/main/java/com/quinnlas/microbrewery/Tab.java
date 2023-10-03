package com.quinnlas.microbrewery;

import java.util.HashMap;

public class Tab {
  HashMap<Beer, Integer> beerQuantities = new HashMap<>();
  public record Beer(String name, double price){};
  public Tab() {
    this.beerQuantities = new HashMap<Beer, Integer>();
  }
}

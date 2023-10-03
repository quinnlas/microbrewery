package com.quinnlas.microbrewery;

import java.util.HashMap;

import com.quinnlas.microbrewery.Tab.Beer;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/order")
public class OrderController {
  // In a realistic application, this would be stored in a DB.
  private HashMap<String, Tab> customerTabs = new HashMap<>();
  private final InventoryClient inventoryClient;

  public OrderController(InventoryClient inventoryClient) {
    this.inventoryClient = inventoryClient;
  }

  @Post
  public String index(String customer, String beerName) {
    // pour the beer
    double price = this.inventoryClient.pour(beerName);

    // find the customer's tab
    Tab tab = this.customerTabs.get(customer);

    // create a tab if it doesn't exist
    if (tab == null) {
      tab = new Tab();
      customerTabs.put(customer, tab);
    }

    // add the beer to the tab if it doesn't exist
    Beer beer = new Beer(beerName, price);
    Integer quantity = tab.beerQuantities.get(beer);
    if (quantity == null) quantity = 1;
    else quantity += 1;
    tab.beerQuantities.put(beer, quantity);

    return beerName;
  }
}

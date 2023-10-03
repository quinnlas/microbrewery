package com.quinnlas.microbrewery;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;

@Client(id = "inventory")
public interface InventoryClient {
  @Get("/tap_list")
  public String test();

  @Put("/tap_list/pour")
  public double pour(String name);
}

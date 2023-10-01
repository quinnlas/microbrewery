package com.quinnlas.microbrewery;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/tap_list")
public class TapListController {
  @Get
  public Beer[] index() {
    Beer[] beers = new Beer[1];
    beers[0] = new Beer("Ball Bearing", "Tangy", 10000);
    return beers;
  }
}

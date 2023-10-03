package com.quinnlas.microbrewery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;

@Controller("/tap_list")
public class TapListController {
  // this would be in a DB normally
  private ArrayList<Beer> beerStock;

  public TapListController() {
    this.beerStock = new ArrayList<Beer>(Arrays.asList(new Beer[] {
        new Beer("Ball Bearing", "Tangy", 10000, 4.95),
        new Beer("10 Foot Pole", "Earthy", 10, 11.02),
        new Beer("Chwinga", "Magically Delicious", 1, 7.50)
    }));

  }

  @Get
  public ArrayList<Beer> index() {
    return this.beerStock;
  }

  @Put("/pour")
  public double pour(@Body PourInfo pourInfo) {
    // find the beer in stock
    OptionalInt beerIndexOpt = IntStream.range(0, this.beerStock.size())
        .filter(i -> this.beerStock.get(i).getName().equals(pourInfo.name()))
        .findFirst();
    if (beerIndexOpt.isEmpty())
      throw new Error("Tried to pour an out-of-stock beer");
    int beerIndex = beerIndexOpt.getAsInt();
    Beer pouredBeer = this.beerStock.get(beerIndex);
    pouredBeer.setQuantity(pouredBeer.getQuantity() - 1);
    if (pouredBeer.getQuantity() <= 0)
      this.beerStock.remove(beerIndex);
    return pouredBeer.getPrice();
  }
}

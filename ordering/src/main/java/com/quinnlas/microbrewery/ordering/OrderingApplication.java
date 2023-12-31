package com.quinnlas.microbrewery.ordering;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class OrderingApplication {
    public static void main(String[] args) {
      SpringApplication.run(OrderingApplication.class, args);
    }

		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}

    @GetMapping("/tap_list")
    public Beer[] tapList(RestTemplate restTemplate) {
			// get the tap list from Inventory
			return restTemplate.getForObject("http://localhost:8080/tap_list", Beer[].class);
    }

    @PostMapping("/order")
    public String order(@RequestBody Object body, RestTemplate restTemplate) {
      // send the order to Serving
      return restTemplate.postForObject("http://localhost:8081/order", body, String.class);
    }

    @PostMapping("/pay")
    public double pay(@RequestBody Object body, RestTemplate restTemplate) {
      // send the order to Serving
      return restTemplate.postForObject("http://localhost:8081/order/pay", body, Double.class);
    }
}
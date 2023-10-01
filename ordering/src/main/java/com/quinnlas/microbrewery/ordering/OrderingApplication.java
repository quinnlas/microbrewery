package com.quinnlas.microbrewery.ordering;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
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
}
package com.marketlogic.projectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * This class is application startup class
 * @author Rajeev Mitra
 *
 */
@SpringBootApplication
public class Application {
	
	public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    } 
	
	@Bean
   public RestTemplate getRestTemplate() {
      return new RestTemplate();
   }

}

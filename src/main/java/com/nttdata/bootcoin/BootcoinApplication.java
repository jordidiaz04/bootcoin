package com.nttdata.bootcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Bootcoin application, enable eureka client.
 */
@EnableEurekaClient
@SpringBootApplication
public class BootcoinApplication {
  public static void main(String[] args) {
    SpringApplication.run(BootcoinApplication.class, args);
  }
}

package org.dcistudent.sakilarest;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class SakilaRestApplication {

  public static void main(String @NotNull [] args) {
    SpringApplication.run(SakilaRestApplication.class, args);
  }

  @Bean
  public @NotNull RestTemplate restTemplate() {
    return new RestTemplate();
  }

}

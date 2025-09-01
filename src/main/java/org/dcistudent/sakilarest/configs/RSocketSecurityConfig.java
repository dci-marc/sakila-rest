package org.dcistudent.sakilarest.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;

@Configuration
@EnableRSocketSecurity
public class RSocketSecurityConfig {

  @Bean
  public @NotNull PayloadSocketAcceptorInterceptor rsocketSecurity(RSocketSecurity rsocket) {
    rsocket
        .authorizePayload(authorize -> authorize.anyExchange().permitAll());

    return rsocket.build();
  }
}

package org.dcistudent.sakilarest.configs;

import org.dcistudent.sakilarest.handlers.AccessDeniedEntryPoint;
import org.dcistudent.sakilarest.handlers.UnauthorizedEntryPoint;
import org.dcistudent.sakilarest.security.AudienceValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final @NotNull Auth0Config auth0Config;

  public SecurityConfig(@NotNull Auth0Config auth0Config) {
    this.auth0Config = auth0Config;
  }

  @Bean
  public @NotNull SecurityFilterChain securityFilterChain(
      @NotNull HttpSecurity http,
      @NotNull UnauthorizedEntryPoint unauthorizedEntryPoint,
      @NotNull AccessDeniedEntryPoint accessDeniedEntryPoint
  )
      throws Exception {
    http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/**", "/api/v1/**")
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**", "/api/v1/auth/**").permitAll()
            .requestMatchers("/**", "/api/v1/**").authenticated()
            .anyRequest().denyAll()
        )
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint(unauthorizedEntryPoint)
            .accessDeniedHandler(accessDeniedEntryPoint)
        )
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(Customizer.withDefaults())
            .authenticationEntryPoint(unauthorizedEntryPoint)
            .accessDeniedHandler(accessDeniedEntryPoint)
        );

    return http.build();
  }

  @Bean
  public @NotNull JwtDecoder jwtDecoder(@NotNull OAuth2ResourceServerProperties properties) {
    String issuer = this.auth0Config.getIssuer();

    NimbusJwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuer);
    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(this.auth0Config.getAudience());
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

    decoder.setJwtValidator(validator);

    return decoder;
  }
}

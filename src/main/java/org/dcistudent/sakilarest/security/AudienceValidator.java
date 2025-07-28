package org.dcistudent.sakilarest.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
  private final @NotNull String audience;

  public AudienceValidator(@NotNull String audience) {
    this.audience = audience;
  }

  @Override
  public @NotNull OAuth2TokenValidatorResult validate(@NotNull Jwt jwt) {
    if (jwt.getAudience().contains(this.audience)) {
      return OAuth2TokenValidatorResult.success();
    }

    return OAuth2TokenValidatorResult.failure(
        new OAuth2Error("invalid_token", "auth:audience.missing", "")
    );
  }
}

package org.dcistudent.sakilarest.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.config.ConfigDataLocation;
import org.springframework.boot.context.config.ConfigDataLocationResolver;
import org.springframework.boot.context.config.ConfigDataLocationResolverContext;

import java.util.Collections;
import java.util.List;

public class DotenvConfigLocationResolver implements ConfigDataLocationResolver<DotenvConfigResource> {

  @Override
  public boolean isResolvable(
      @NotNull ConfigDataLocationResolverContext context,
      @NotNull ConfigDataLocation location
  ) {
    return location.hasPrefix("dotenv:");
  }

  @Override
  public @NotNull List<DotenvConfigResource> resolve(
      @NotNull ConfigDataLocationResolverContext context,
      @NotNull ConfigDataLocation location
  ) {
    @NotNull String path = location.getValue().substring("dotenv:".length());
    @NotNull String directory = ".";
    @NotNull String filename = ".env";

    if (path.contains("/")) {
      int lastSlash = path.lastIndexOf('/');
      directory = path.substring(0, lastSlash);
      filename = path.substring(lastSlash + 1);

      return Collections.singletonList(new DotenvConfigResource(directory, filename));
    }

    return Collections.singletonList(new DotenvConfigResource(directory, filename));
  }
}

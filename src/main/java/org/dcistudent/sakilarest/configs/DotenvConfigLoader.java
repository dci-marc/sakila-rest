package org.dcistudent.sakilarest.configs;

import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.config.ConfigData;
import org.springframework.boot.context.config.ConfigDataLoader;
import org.springframework.boot.context.config.ConfigDataLoaderContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DotenvConfigLoader implements ConfigDataLoader<DotenvConfigResource> {

  @Override
  public @NotNull ConfigData load(@NotNull ConfigDataLoaderContext context, DotenvConfigResource resource) {
    Dotenv dotenv = Dotenv
        .configure()
        .directory(resource.getDirectory())
        .filename(resource.getFilename())
        .ignoreIfMissing()
        .load();

    Map<String, Object> props = new HashMap<>();
    dotenv.entries().forEach(e -> props.put(e.getKey(), e.getValue()));

    return new ConfigData(
        List.of(new MapPropertySource("dotenv", props))
    );
  }
}

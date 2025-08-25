package org.dcistudent.sakilarest.configs;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;

import java.net.URI;

@Component
@ConfigurationProperties(prefix = "s3")
public final class S3Config {

  private @NotNull String bucket = "";
  private @NotNull Region region = Region.US_EAST_1;
  private @NotNull String accessKey = "";
  private @NotNull String secretKey = "";
  private @NotNull URI url = URI.create("");

  public @NotNull String getBucket() {
    return this.bucket;
  }

  public @NotNull Region getRegion() {
    return this.region;
  }

  public @NotNull String getAccessKey() {
    return this.accessKey;
  }

  public @NotNull String getSecretKey() {
    return this.secretKey;
  }

  public @NotNull URI getUrl() {
    return this.url;
  }

  public void setBucket(@NotNull String bucket) {
    this.bucket = bucket;
  }

  public void setRegion(@NotNull String region) {
    this.region = Region.of(region);
  }

  public void setAccessKey(@NotNull String accessKey) {
    this.accessKey = accessKey;
  }

  public void setSecretKey(@NotNull String secretKey) {
    this.secretKey = secretKey;
  }

  public void setUrl(@NotNull String url) {
    this.url = URI.create(url);
  }

  public static final class Builder implements Buildable {
    private @NotNull String bucket = "";
    private @NotNull String region = "";
    private @NotNull String accessKey = "";
    private @NotNull String secretKey = "";
    private @NotNull String url = "";

    public @NotNull Builder setBucket(@NotNull String bucket) {
      this.bucket = bucket;
      return this;
    }

    public @NotNull Builder setRegion(@NotNull String region) {
      this.region = region;
      return this;
    }

    public @NotNull Builder setAccessKey(@NotNull String accessKey) {
      this.accessKey = accessKey;
      return this;
    }

    public @NotNull Builder setSecretKey(@NotNull String secretKey) {
      this.secretKey = secretKey;
      return this;
    }

    public @NotNull Builder setUrl(@NotNull String url) {
      this.url = url;
      return this;
    }

    public @NotNull S3Config build() {
      S3Config config = new S3Config();
      config.setBucket(this.bucket);
      config.setRegion(this.region);
      config.setAccessKey(this.accessKey);
      config.setSecretKey(this.secretKey);
      config.setUrl(this.url);
      return config;
    }
  }
}

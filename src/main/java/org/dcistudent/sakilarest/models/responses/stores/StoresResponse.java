package org.dcistudent.sakilarest.models.responses.stores;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.dcistudent.sakilarest.interfaces.models.responses.DomainResponse;
import org.dcistudent.sakilarest.interfaces.models.responses.Buildable;
import org.dcistudent.sakilarest.models.responses.StaffResponse;
import org.dcistudent.sakilarest.models.responses.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@JsonDeserialize(builder = StoresResponse.Builder.class)
public final class StoresResponse extends AbstractUuidResponse implements DomainResponse {

  private final @NotNull String lastUpdate;
  private final @NotNull StaffResponse staff;

  public StoresResponse(@NotNull Builder builder) {
    super(builder.uuid);
    this.lastUpdate = builder.lastUpdate;
    this.staff = builder.staff;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }

  public @NotNull StaffResponse getStaff() {
    return this.staff;
  }

  @JsonPOJOBuilder(withPrefix = "set")
  public static final class Builder implements Buildable<StoresResponse> {
    private @NotNull UUID uuid = UUID.randomUUID();
    private @NotNull String lastUpdate = "";
    private @NotNull StaffResponse staff = new StaffResponse.Builder().build();

    public @NotNull Builder setUuid(@NotNull UUID uuid) {
      this.uuid = uuid;
      return this;
    }

    public @NotNull Builder setLastUpdate(@NotNull String lastUpdate) {
      this.lastUpdate = lastUpdate;
      return this;
    }

    public @NotNull Builder setStaff(@NotNull StaffResponse staff) {
      this.staff = staff;
      return this;
    }

    @JsonCreator
    public @NotNull StoresResponse build() {
      return new StoresResponse(this);
    }
  }
}

package org.dcistudent.sakilarest.serializers.responses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class EmptyResponseSerializer extends JsonSerializer<EmptyResponse> {

  @Override
  public void serialize(
      @NotNull EmptyResponse value, @NotNull JsonGenerator gen,
      @NotNull SerializerProvider serializers
  ) throws IOException {
    gen.writeString("");
  }
}

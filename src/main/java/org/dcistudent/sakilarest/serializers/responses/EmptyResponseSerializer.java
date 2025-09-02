package org.dcistudent.sakilarest.serializers.responses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class EmptyResponseSerializer<T> extends JsonSerializer<T> {

  @Override
  public void serialize(
      @NotNull T value,
      @NotNull JsonGenerator gen,
      @NotNull SerializerProvider serializers
  ) throws IOException {
    gen.writeString("");
  }
}

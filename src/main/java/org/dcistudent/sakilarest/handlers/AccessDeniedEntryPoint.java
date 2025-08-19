package org.dcistudent.sakilarest.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedEntryPoint implements AccessDeniedHandler {

  @Override
  public void handle(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull AccessDeniedException accessDeniedException
  ) throws IOException {
    @NotNull ResponseEntity<Response<EmptyResponse>> responseModel = ResponseEntity
        .status(HttpStatus.FORBIDDEN.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            HttpStatus.FORBIDDEN,
            "error:access:denied"
        ));

    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");
    response.getWriter().write((new ObjectMapper()).writeValueAsString(responseModel));
  }
}

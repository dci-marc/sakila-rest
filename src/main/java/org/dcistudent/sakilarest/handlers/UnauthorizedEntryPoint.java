package org.dcistudent.sakilarest.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dcistudent.sakilarest.factories.shared.ResponseFactory;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull AuthenticationException authException
  ) throws IOException {
    @NotNull ResponseEntity<Response<EmptyResponse>> responseModel = ResponseEntity
        .status(HttpStatus.UNAUTHORIZED.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            HttpStatus.UNAUTHORIZED,
            "auth:user:login:fail"
        ));

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    response.getWriter().write((new ObjectMapper()).writeValueAsString(responseModel));
  }
}

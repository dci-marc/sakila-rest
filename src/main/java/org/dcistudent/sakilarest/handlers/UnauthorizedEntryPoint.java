package org.dcistudent.sakilarest.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull AuthenticationException authException
  )
      throws java.io.IOException {
    @NotNull Response<EmptyResponse> responseModel = ResponseFactory.create(
        HttpStatus.UNAUTHORIZED.value(),
        "error:authentication:fail"
    );

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    response.getWriter().write((new ObjectMapper()).writeValueAsString(responseModel));
  }
}

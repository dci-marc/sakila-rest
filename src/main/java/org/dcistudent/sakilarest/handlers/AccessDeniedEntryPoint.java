package org.dcistudent.sakilarest.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedEntryPoint implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    Response<String> responseModel = ResponseFactory.create(
        HttpStatus.FORBIDDEN.value(),
        "error:access:denied"
    );

    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");
    response.getWriter().write((new ObjectMapper()).writeValueAsString(responseModel));
  }
}

package org.dcistudent.sakilarest.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Profile("dev")
public final class RequestLoggingFilter extends OncePerRequestFilter {

  private static final @NotNull Logger LOG = Logger.getLogger(RequestLoggingFilter.class.getName());

  @Override
  protected void doFilterInternal(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain
  ) throws ServletException, IOException {
    ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
    filterChain.doFilter(wrappedRequest, response);

    if (LOG.isLoggable(Level.INFO)) {
      byte[] content = wrappedRequest.getContentAsByteArray();
      if (content.length > 0) {
        String requestBody = new String(content, wrappedRequest.getCharacterEncoding());
        requestBody = requestBody.replaceAll("[\n\r]", "_");
        LOG.info("Request Body: " + requestBody);
      }
    }
  }
}

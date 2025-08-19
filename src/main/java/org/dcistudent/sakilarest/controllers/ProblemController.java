package org.dcistudent.sakilarest.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;

@RestController
@RequestMapping("/provoke-error")
public class ProblemController {

  public static final @NotNull String MAPPING_BASE = "/provoke-error";

  @GetMapping("/runtime")
  @SuppressWarnings({
      "java:S112"
  })
  public void provokeRuntimeError() {
    throw Problem
        .builder()
        .withType(URI.create("https://example.org/provoke-error/runtime"))
        .withTitle("Runtime Error")
        .withStatus(Status.INTERNAL_SERVER_ERROR)
        .withDetail("This is a runtime error for testing purposes.")
        .withInstance(URI.create("https://example.org/provoke-error/runtime/instance"))
        .build();
  }

  @GetMapping("/null-pointer")
  @SuppressWarnings({
      "java:S2259",               // possible NPE dereference
      "java:S899",                // ignored return value
      "java:S878",                // alternative for ignored return value
      "unused",                   // Unused variable
      "ReturnValueIgnored",       // Error Prone: return value of length() ignored
      "NullableDereference",      // Error Prone: possible NPE
      "ResultOfMethodCallIgnored" // IntelliJ IDEA: result of length() ignored
  })
  public void provokeNullPointerError() {
    try {
      // This will throw a NullPointerException
      String str = null;
      str.length(); // NOSONAR
    } catch (NullPointerException e) {
      throw Problem
          .builder()
          .withType(URI.create("https://example.org/provoke-error/null-pointer"))
          .withTitle("Null Pointer Error")
          .withStatus(Status.INTERNAL_SERVER_ERROR)
          .withDetail("This is a null pointer error for testing purposes.")
          .withInstance(URI.create("https://example.org/provoke-error/null-pointer/instance"))
          .build();
    }
  }

  @GetMapping("/illegal-argument")
  public void provokeIllegalArgumentError() {
    try {
      // This will throw an IllegalArgumentException
      String arg = null;
      if (arg == null) {
        throw new IllegalArgumentException("Argument cannot be null");
      }
    } catch (IllegalArgumentException e) {
      throw Problem
          .builder()
          .withType(URI.create("https://example.org/provoke-error/illegal-argument"))
          .withTitle("Illegal Argument Error")
          .withStatus(Status.BAD_REQUEST)
          .withDetail("This is an illegal argument error for testing purposes.")
          .withInstance(URI.create("https://example.org/provoke-error/illegal-argument/instance"))
          .build();
    }
  }

  @GetMapping("/arithmetic")
  @SuppressWarnings({
      "java:S2925",               // Remove this conditional structure or edit its code blocks
      "java:S3518",               // Divide by zero will raise exception
      "unused",                   // Unused variable
      "divzero"                   // SpotBugs: division by constant zero
  })
  public void provokeArithmeticError() {
    try {
      // This will throw an ArithmeticException (division by zero)
      int result = 1 / 0; // NOSONAR
    } catch (ArithmeticException e) {
      throw Problem
          .builder()
          .withType(URI.create("https://example.org/provoke-error/arithmetic"))
          .withTitle("Arithmetic Error")
          .withStatus(Status.INTERNAL_SERVER_ERROR)
          .withDetail("This is an arithmetic error for testing purposes.")
          .withInstance(URI.create("https://example.org/provoke-error/arithmetic/instance"))
          .build();
    }
  }
}

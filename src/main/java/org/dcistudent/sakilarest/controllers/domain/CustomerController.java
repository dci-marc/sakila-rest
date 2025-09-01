package org.dcistudent.sakilarest.controllers.domain;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.responses.shared.ResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Paged;
import org.dcistudent.sakilarest.models.requests.shared.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.customers.CustomerPageResponse;
import org.dcistudent.sakilarest.models.responses.domain.customers.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.services.domain.CustomerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/stores/{id}") // plural nouns
public final class CustomerController {

  private final @NotNull CustomerService service;

  public CustomerController(@NotNull CustomerService service) {
    this.service = service;
  }

  @GetMapping("/customers")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully fetched customers for the store.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CustomerPageResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Customers not found or invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<Paged<CustomerResponse>>> getStoreCustomers(
      @NotNull @PathVariable UUID id,
      @NotNull @ModelAttribute @Valid LimitOffsetRequest request
  ) {
    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.OK,
            "customers:fetch:success",
            this.service.getAll(id, request)
        )
    );
  }

  @GetMapping("/customers/{customerId}")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully fetched customer details.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CustomerResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Customer not found or invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<CustomerResponse>> getStoreCustomer(
      @NotNull @PathVariable UUID id,
      @NotNull @PathVariable UUID customerId
  ) {
    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.OK,
            "customer:fetch:success",
            this.service.getCustomer(id, customerId)
        )
    );
  }
}

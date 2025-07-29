package org.dcistudent.sakilarest.models.responses;

public sealed interface Auth0Response extends ResponsePayload
    permits Auth0ErrorResponse {
}

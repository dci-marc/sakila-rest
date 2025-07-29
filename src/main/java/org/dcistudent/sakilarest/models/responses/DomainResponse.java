package org.dcistudent.sakilarest.models.responses;

public sealed interface DomainResponse extends ResponsePayload
    permits CustomerResponse, FilmResponse, StoreResponse, UserResponse {
}

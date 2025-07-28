package org.dcistudent.sakilarest.models.responses;

public sealed interface ResponsePayload
    permits Auth0ErrorResponse, DictionaryListResponse, EmptyResponse, FilmResponse, StoreResponse, UserResponse {
}

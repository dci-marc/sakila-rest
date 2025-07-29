package org.dcistudent.sakilarest.models.responses;

public sealed interface ResponsePayload
    permits Auth0Response, DictionaryListResponse, DomainResponse, EmptyResponse {
}

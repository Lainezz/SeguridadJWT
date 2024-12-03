package com.es.jwtsecurity.error.exception;

public class NotAuthorizedException extends RuntimeException {

    private static final String DESCRIPCION = "Not Authorized (401)";

    public NotAuthorizedException(String mensaje) {
        super(DESCRIPCION +". "+ mensaje);
    }
}
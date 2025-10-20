package com.recruitify.server.Util.Error;

public class EntityNotFoundException extends RepositoryException{
    public EntityNotFoundException(String entityName, Object id) {
        super(String.format("%s with id %s not found", entityName, id));
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

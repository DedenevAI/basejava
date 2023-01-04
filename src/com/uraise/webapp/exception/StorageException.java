package com.uraise.webapp.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String errorMessage, String uuid) {
        super(errorMessage);
        this.uuid = uuid;
    }
    public StorageException(String errorMessage, String uuid, Exception e) {
        super(errorMessage, e);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}

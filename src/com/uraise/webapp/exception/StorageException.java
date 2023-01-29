package com.uraise.webapp.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String errorMessage, String uuid) {
        super(errorMessage);
        this.uuid = uuid;
    }
    public StorageException(String errorMessage, Exception e) {
        this(errorMessage, null, e);
    }
    public StorageException(String errorMessage, String uuid, Exception e) {
        super(errorMessage, e);
        this.uuid = uuid;
    }

    public StorageException(Exception e) {
        this(e.getMessage(), e);
    }

    public String getUuid() {
        return uuid;
    }
}

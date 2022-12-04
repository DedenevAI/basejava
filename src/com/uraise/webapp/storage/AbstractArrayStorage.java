package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: No such item in the storage");
            return null;
        } else {
            return storage[index];
        }
    }

    protected abstract int getIndex(String uuid);
}

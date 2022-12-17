package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstarctStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
}

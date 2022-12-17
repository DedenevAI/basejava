package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;
    protected abstract Integer getSearchKey(String uuid);

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume r, int index);

    @Override
    protected boolean isExit(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage[(Integer) index] = r;
    }

    @Override
    protected void doSave(Object index, Resume r) {
        if (storage.length == size) {
            throw new StorageException("ERROR: Storage overflow", r.getUuid());
        } else {
            saveResume(r, (Integer) index);
            size++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        deleteResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

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

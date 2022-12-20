package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;
    protected abstract Integer getSearchKey(String uuid);

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume r, int index);

    @Override
    protected boolean isExited(Integer index) {
        return index >= 0;
    }

    @Override
    protected void updateResume(Integer index, Resume r) {
        storage[index] = r;
    }

    @Override
    protected void doSave(Integer index, Resume r) {
        if (storage.length == size) {
            throw new StorageException("ERROR: Storage overflow", r.getUuid());
        } else {
            saveResume(r, index);
            size++;
        }
    }

    @Override
    protected void doDelete(Integer index) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    public List<Resume> copyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

}

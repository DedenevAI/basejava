package com.uraise.webapp.storage;

import com.uraise.webapp.exception.ExistStorageException;
import com.uraise.webapp.exception.NotExistStorageException;
import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstarctStorage {


    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (storage.length == size) {
            throw new StorageException("ERROR: Storage overflow", r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(r, index);
            size++;
        }
    }


    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
            System.out.println("Update " + r + " completed");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }



    protected abstract int getIndex(String uuid);

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume r, int index);
}

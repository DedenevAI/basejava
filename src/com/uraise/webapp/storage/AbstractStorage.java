package com.uraise.webapp.storage;

import com.uraise.webapp.exception.ExistStorageException;
import com.uraise.webapp.exception.NotExistStorageException;
import com.uraise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);
    protected abstract void updateResume(Object searchKey, Resume r);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    public void update(Resume r) {
        Object searchKey = getExisted(r.getUuid());
        updateResume(searchKey, r);
        System.out.println("Updating " + r + " has completed");
    }

    public void save(Resume r) {
        Object searchKey = getNotExisted(r.getUuid());
        doSave(searchKey, r);
    }

    public void delete(String uuid) {
        Object searchKey = getExisted(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExisted(uuid);
        return getResume(searchKey);
    }

    private Object getExisted(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExit(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExisted(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExit(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExit(Object searchKey);
}

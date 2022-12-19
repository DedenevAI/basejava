package com.uraise.webapp.storage;

import com.uraise.webapp.exception.ExistStorageException;
import com.uraise.webapp.exception.NotExistStorageException;
import com.uraise.webapp.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {

    public void update(Resume r) {
        SK searchKey = getExisted(r.getUuid());
        updateResume(searchKey, r);
        System.out.println("Updating " + r + " has completed");
    }

    public void save(Resume r) {
        SK searchKey = getNotExisted(r.getUuid());
        doSave(searchKey, r);
    }

    public void delete(String uuid) {
        SK searchKey = getExisted(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getExisted(uuid);
        return getResume(searchKey);
    }

    private SK getExisted(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExited(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExisted(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExited(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExited(SK searchKey);
    protected abstract SK getSearchKey(String uuid);
    protected abstract void updateResume(SK searchKey, Resume r);

    protected abstract void doSave(SK searchKey, Resume r);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume getResume(SK searchKey);

}

package com.uraise.webapp.storage;

import com.uraise.webapp.exception.ExistStorageException;
import com.uraise.webapp.exception.NotExistStorageException;
import com.uraise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        Comparator<Resume> comparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> list = copyAll();
        list.sort(comparator);
        return list;
    }

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getExisted(r.getUuid());
        updateResume(searchKey, r);
        System.out.println("Updating " + r + " has completed");
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getNotExisted(r.getUuid());
        doSave(searchKey, r);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExisted(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExisted(uuid);
        return getResume(searchKey);
    }

    private SK getExisted(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExisted(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume" + uuid + "already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(SK searchKey);
    protected abstract SK getSearchKey(String uuid);
    protected abstract void updateResume(SK searchKey, Resume r);

    protected abstract void doSave(SK searchKey, Resume r);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume getResume(SK searchKey);
    protected abstract List<Resume> copyAll();

}

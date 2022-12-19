package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> map = new LinkedHashMap<String, Resume>;

    @Override
    protected Integer getSearchKey(String uuid) {
    }

    @Override
    protected void updateResume(Object searchKey, Resume r) {

    }

    @Override
    protected void doSave(Object searchKey, Resume r) {

    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Resume getResume(Object searchKey) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected boolean isExited(Object searchKey) {
        return false;
    }
}

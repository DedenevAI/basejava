package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new LinkedHashMap<String, Resume>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(String searchKey, Resume r) {
        storage.put(searchKey,r);
    }

    @Override
    protected void doSave(String searchKey, Resume r) {
        storage.put(searchKey,r);
    }

    @Override
    protected void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
       return storage.size();
    }

    @Override
    protected boolean isExited(String searchKey) {
        return storage.containsKey(searchKey);
    }
}

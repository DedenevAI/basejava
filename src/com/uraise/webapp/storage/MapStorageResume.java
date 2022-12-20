package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage<Resume>{
    private final Map<String, Resume> storage = new HashMap<>();
    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void updateResume(Resume resume, Resume r) {
        storage.put(resume.getUuid(),resume);
    }

    @Override
    protected void doSave(Resume resume, Resume r) {
        storage.put(resume.getUuid(),resume);
    }

    @Override
    protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    protected Resume getResume(Resume resume) {
        return resume;
    }

    @Override
    protected List<Resume> copyAll() {
        return Arrays.asList(storage.values().toArray(new Resume[0]));
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}

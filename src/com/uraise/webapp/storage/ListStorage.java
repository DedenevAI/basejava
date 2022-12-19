package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int index = 0; index < listStorage.size(); index++){
            if (listStorage.get(index).getUuid().equals(uuid)) {
                return index;
            }
        }
        return null;
    }

    @Override
    protected void updateResume(Integer searchKey, Resume r) {
        listStorage.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Integer searchKey, Resume r) {
        listStorage.add(r);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        listStorage.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return listStorage.get((Integer) searchKey);
    }

    @Override
    protected boolean isExited(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}

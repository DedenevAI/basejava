package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveResume(Resume r, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage,insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int numMove = size - index - 1;
        if (numMove > 0){
            System.arraycopy(storage,index + 1, storage, index, numMove);
        }

    }

    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

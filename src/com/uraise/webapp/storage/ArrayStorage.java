package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected void saveResume(Resume r, int index) {
        storage[size] = r;
    }


    protected void deleteResume(int index) {
        storage[index] = storage[size - 1];
    }

    /**
     * @return the index of the element or -1 if the element does not exist
     */

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].getUuid(), uuid)) {
                return i;
            }
        }
        return -1;
    }

}

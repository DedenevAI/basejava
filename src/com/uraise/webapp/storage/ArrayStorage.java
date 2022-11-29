package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("ERROR: Resume " + r.getUuid() + " is already in storage");
            return;
        }
        if (storage.length == size) {
            System.out.println("ERROR: Storage is full");
        } else {
            storage[size] = r;
            size++;
        }

    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ERROR: Resume " + r.getUuid() + " not exit in storage");
        } else {
            storage[index] = r;
            System.out.println("Update " + r + " completed");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        return index == -1 ? null : storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: No item for delete");
        } else {
            storage[index] = storage[size - 1];
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    /**
     * @return the index of the element or -1 if the element does not exist
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].getUuid(), uuid)) {
                return i;
            }
        }
        return -1;
    }
}

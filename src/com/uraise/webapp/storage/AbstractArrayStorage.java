package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (storage.length == size) {
            System.out.println("ERROR: Storage is full");
        } else if (index > 0) {
            System.out.println("ERROR: Resume " + r.getUuid() + " is already in storage");
        } else {
            saveResume(r, index);
            size++;
        }
    }


    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("ERROR: Resume " + r.getUuid() + " isn't exit in storage");
        } else {
            storage[index] = r;
            System.out.println("Update " + r + " completed");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: No item for delete");
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: No such item in the storage");
            return null;
        } else {
            return storage[index];
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

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume r, int index);
}

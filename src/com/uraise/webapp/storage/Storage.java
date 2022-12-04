package com.uraise.webapp.storage;

import com.uraise.webapp.model.Resume;

public interface Storage {
    void clear();

    void save(Resume r);

    void update(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
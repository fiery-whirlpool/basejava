package com.webapp.storage;

import com.webapp.model.Resume;

public interface Storage {

    void clear();

    void update(Resume r);

    Resume get(String uuid);

    Resume[] getAll();

    int size();

    void save(Resume r);

    void delete(String uuid);
}

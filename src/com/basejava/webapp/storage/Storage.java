package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public interface Storage {

    void clear();

    void save(Resume resume);

    void delete(String uuid);

    void update(Resume resume);

    Resume get(String uuid);

    Resume[] getAll();

    int size();
}

package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public interface Storage {

    void clear();

    void save();

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    void update(Resume resume);

    int size();
}

package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void save(Resume resume);

    void delete(String uuid);

    void update(Resume resume);

    Resume get(String uuid);

    List<Resume> getAllSorted();

    int size();
}

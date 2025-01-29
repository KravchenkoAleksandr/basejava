package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void addResume(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    public void removeResume(Object searchKey) {
        storage.remove((int) searchKey);
    }

    public void updateResume(Resume resume, Object searchKey) {
        storage.set((Integer) searchKey, resume);
    }

    public Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    public List<Resume> getAll() {
        return storage;
    }


    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

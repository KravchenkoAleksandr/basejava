package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

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
        storage.set((int) searchKey, resume);
    }

    public Resume getResume(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    public Resume[] getAllResume() {
        return storage.toArray(new Resume[0]);
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
    protected Object getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

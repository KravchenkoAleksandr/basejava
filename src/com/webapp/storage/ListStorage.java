package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doSave(Integer searchKey, Resume resume) {
        storage.add(resume);
    }

    public void doDelete(Integer searchKey) {
        storage.remove((searchKey).intValue());
    }

    public void doUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    public Resume doGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public List<Resume> doCopyAll() {
        return storage;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
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
        return null;
    }
}

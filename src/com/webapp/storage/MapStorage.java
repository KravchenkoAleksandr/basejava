package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new LinkedHashMap<>();


    @Override
    protected void addResume(Object searchKey, Resume resume) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void removeResume(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.replace((String) searchKey, resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(List.of(storage.values().toArray(new Resume[0])));
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}


package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new LinkedHashMap<>();


    @Override
    protected void addResume(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(Object searchKey) {
        if (searchKey instanceof String) {
            storage.remove(searchKey);
        }
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
    protected Object getIndex(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }


    @Override
    protected Resume[] getAllResume() {
        return storage.values().toArray(new Resume[0]);

    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey instanceof String && storage.containsKey(searchKey);
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


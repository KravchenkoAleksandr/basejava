package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage {

    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected void addResume(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.replace(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume resume = storage.get(uuid);
        return (resume != null) ? resume : uuid;
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(List.of(storage.values().toArray(new Resume[0])));
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey instanceof Resume;
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

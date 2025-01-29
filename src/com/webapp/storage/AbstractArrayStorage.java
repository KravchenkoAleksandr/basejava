package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void addResume(Object searchKey, Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage oferflow", resume.getUuid());
        }
        saveResume(resume, (Integer) searchKey);
        size++;
    }

    public void removeResume(Object searchKey) {
        deleteResume((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    public void updateResume(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    public final Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    public List<Resume> getAll() {
        return Arrays.asList(storage);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume resume, int index);
}

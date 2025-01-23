package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void addResume(Resume resume, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage oferflow", resume.getUuid());
        }
        saveResume(resume, index);
        size++;
    }

    public void removeResume(int index) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    public final Resume getResume(int index) {
        return storage[index];
    }

    public Resume[] getAllResume() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume resume, int index);
}

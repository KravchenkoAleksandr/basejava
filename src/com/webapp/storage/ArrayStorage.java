package com.webapp.storage;

import com.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    public void deleteResume(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
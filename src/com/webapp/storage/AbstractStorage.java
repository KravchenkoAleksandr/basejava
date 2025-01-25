package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        addResume(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        removeResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        updateResume(resume, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    protected abstract void addResume(Resume resume, Object searchKey);

    protected abstract void removeResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract Object getIndex(String uuid);

    protected abstract Resume[] getAllResume();

    protected abstract boolean isExist(Object searchKey);

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getIndex(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}

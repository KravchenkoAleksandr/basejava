package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        addResume(searchKey, resume);
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
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = getAll();
        sortedList.sort(COMPARATOR);
        return sortedList;
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void addResume(Object searchKey, Resume resume);

    protected abstract void removeResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract List<Resume> getAll();

    protected abstract boolean isExist(Object searchKey);
}

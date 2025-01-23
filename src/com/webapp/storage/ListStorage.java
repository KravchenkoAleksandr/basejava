package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public void addResume(Resume resume, int index) {
        storageList.add(resume);
    }

    public void removeResume(int index) {
        storageList.remove(index);
    }

    public void updateResume(Resume resume, int index) {
        storageList.set(index, resume);
    }

    public Resume getResume(int index) {
        return storageList.get(index);
    }

    @Override
    public Resume[] getAllResume() {
        return storageList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storageList.indexOf(resume);
    }
}

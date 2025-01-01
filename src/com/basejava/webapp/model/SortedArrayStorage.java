package com.basejava.webapp.model;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Места для добавления резюме недостаточно");
        } else if (index >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " существует");
        } else {
            index = Math.abs(index) - 1;
            storage[index] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            int len = size - index;
            System.arraycopy(storage, index + 1, storage, index, len);
            storage[size] = null;
            return;
        }
        System.out.println("Резюме " + uuid + " не найдено для удаления");
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            return;
        }
        System.out.println("Резюме " + resume.getUuid() + " не найдено для обновления");
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

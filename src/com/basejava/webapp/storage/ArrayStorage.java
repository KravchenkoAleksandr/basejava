package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Места для добавления резюме недостаточно");
        } else if (isExisting(index)) {
            System.out.println("Резюме " + resume.getUuid() + " существует");
        } else {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)) {
            return storage[index];
        }
        throw new NoSuchElementException("Резюме " + uuid + " не найдено");
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)) {
            size--;
            int len = size - index;
            System.arraycopy(storage, index + 1, storage, index, len);
            storage[size] = null;
            return;
        }
        System.out.println("Резюме " + uuid + " не найдено для удаления");
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExisting(index)) {
            storage[index] = resume;
            return;
        }
        System.out.println("Резюме " + resume.getUuid() + " не найдено для обновления");
    }

    private boolean isExisting(int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
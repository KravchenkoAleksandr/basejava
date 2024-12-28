package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayStorage {
    private static final int SUM_NUM_RESUME = 10000;
    private Resume[] storage = new Resume[SUM_NUM_RESUME];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < SUM_NUM_RESUME && !isExists(resume.getUuid())) {
            storage[size++] = resume;
            return;
        }
        String noRoom = "Места для добавления резюме недостаточно";
        String elementAlreadyExists = "Резюме " + resume.getUuid() + " существует";
        System.out.println(size >= SUM_NUM_RESUME ? noRoom : elementAlreadyExists);
    }

    public Resume get(String uuid) {
        if (isExists(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        throw new NoSuchElementException("Резюме " + uuid + " не найдено");
    }

    public void delete(String uuid) {
        if (isExists(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    size--;
                    int len = size - i;
                    System.arraycopy(storage, i + 1, storage, i, len);
                    storage[size] = null;
                    return;
                }
            }
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
        if (isExists(resume.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i] == resume) {
                    storage[i] = resume;
                    return;
                }
            }
        }
        System.out.println("Резюме " + resume.getUuid() + " не найдено для обновления");
    }

    private boolean isExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return true;
        }
        return false;
    }
}
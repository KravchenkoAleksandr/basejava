package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Места для добавления резюме недостаточно");
            return;
        } else if (index >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " существует");
            return;
        } else {
            if (index == -1) {
                storage[size] = resume;
            }
            index = -(index + 1);
            storage[index] = resume;
        }
        size++;
    }

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

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            return;
        }
        System.out.println("Резюме " + resume.getUuid() + " не найдено для обновления");
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}

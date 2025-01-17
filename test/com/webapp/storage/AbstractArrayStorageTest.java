package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final Resume RESUME_1 = new Resume("uuid1");
    private static final Resume RESUME_2 = new Resume("uuid2");
    private static final Resume RESUME_3 = new Resume("uuid3");
    private static final Resume RESUME_4 = new Resume("uuid4");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void update() {
        storage.update(storage.get(RESUME_1.getUuid()));
        assertSame(storage.getAll()[0], storage.get(RESUME_1.getUuid()));
    }

    @Test
    public void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(storage.get("dummy")));
    }

    @Test
    public void getAll() {
        storage.clear();
        Resume[] expected = {RESUME_2, RESUME_3, RESUME_4};
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void saveExistResume() {
        assertThrows(ExistStorageException.class, () -> {
            storage.save(storage.get(RESUME_1.getUuid()));
        });
    }

    @Test
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
            storage.save(new Resume("uuid10_001"));
            fail("Переполнение произошло раньше времени");
        } catch (StorageException e) {
            System.out.println("Тест успешно выполнен");
        }
    }

    @Test
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(RESUME_1.getUuid()));
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    public void get() {
        for (Resume resume : storage.getAll()) {
            assertGet(resume);
        }
    }

    private void assertGet(Resume resume) {
        assertSame(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }
}
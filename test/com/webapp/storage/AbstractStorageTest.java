package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";
    private static final Resume RESUME_1 = new Resume(UUID1, "Петров Петр");
    private static final Resume RESUME_2 = new Resume(UUID2, "Светикова Света");
    private static final Resume RESUME_3 = new Resume(UUID3, "Иванов Иван");
    private static final Resume RESUME_4 = new Resume(UUID4, "Галкина Галя");

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
    }

    @Test
    public void update() {
        storage.update(storage.get(RESUME_1.getUuid()));
        assertSame(storage.get(RESUME_1.getUuid()), storage.get(RESUME_1.getUuid()));
    }

    @Test
    public void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(storage.get("dummy")));
    }

    @Test
    public void getAllSorted() {
        List<Resume> expected = List.of(RESUME_3, RESUME_1, RESUME_2);
        assertEquals(expected, storage.getAllSorted());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void saveExistResume() {
        assertThrows(ExistStorageException.class, () -> storage.save(storage.get(RESUME_1.getUuid())));
    }

    @Test
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(RESUME_1.getUuid()));
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void get() {
        for (Resume resume : storage.getAllSorted()) {
            assertGet(resume);
        }
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    private void assertGet(Resume resume) {
        assertSame(resume, storage.get(resume.getUuid()));
    }
}
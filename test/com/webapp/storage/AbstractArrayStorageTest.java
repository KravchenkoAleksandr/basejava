package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

        @BeforeEach
        public void setUp () {
            storage.clear();
            storage.save(new Resume(UUID_1));
            storage.save(new Resume(UUID_2));
            storage.save(new Resume(UUID_3));
        }

        @Test
        public void size () {
            assertEquals(3, storage.size());
        }

        @Test
        public void clear () {
            storage.clear();
            assertEquals(0, storage.size());
        }

        @Test
        public void update () {
            storage.update(storage.get(UUID_1));
            assertSame(storage.getAll()[0], storage.get(UUID_1));
        }

        @Test
        public void updateNotExist () {
            assertThrows(NotExistStorageException.class, () -> storage.update(storage.get("dummy")));
        }

        @Test
        public void getAll () {
            storage.save(new Resume("uuid4"));
            assertEquals(storage.getAll().length, storage.size());

        }

        @Test
        public void save () {
            storage.save(new Resume("uuid4"));
            assertEquals(4, storage.size());
        }

        @Test
        public void saveExistResume () {
            assertThrows(ExistStorageException.class, () -> {
                storage.save(storage.get(UUID_1));
            });
        }

        @Test
        public void saveOverflow () {
            storage.clear();
            try {
                for (int i = 0; i < 10000; i++) {
                    storage.save(new Resume("uuid" + i));
                }
                storage.save(new Resume("uuid10_001"));
                fail("Переполнение произошло раньше времени");
            }catch (StorageException e) {

            }
        }

        @Test
        public void delete () {
            storage.delete(UUID_1);
            assertEquals(2, storage.size());
        }

        @Test
        public void deleteNotExist () {
            assertThrows(NotExistStorageException.class, () -> {
                storage.delete("dummy");
            });
        }

        @Test
        public void get () {
            assertSame(storage.getAll()[0], storage.get(UUID_1));
        }

        @Test
        public void getNotExist () {
            assertThrows(NotExistStorageException.class, () -> {
                storage.get("dummy");
            });
        }
    }
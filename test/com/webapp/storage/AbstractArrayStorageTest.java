package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveOverflow() {
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
}
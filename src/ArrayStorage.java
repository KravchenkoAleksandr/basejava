import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        int index = size;
        storage[index] = resume;
        size++;
    }

    Resume get(String uuid) {
        int len = size;
        for (int i = 0; i < len; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        throw new NoSuchElementException("Резюме не найдено");
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                size--;
                int len = size - i;
                System.arraycopy(storage, i + 1, storage, i, len);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] listResume = new Resume[size];
        System.arraycopy(storage, 0, listResume, 0, size);
        return listResume;
    }

    int size() {
        return size;
    }
}

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size(), null);
    }

    void save(Resume r) {
        int index = size();
        storage[index] = r;
    }

    Resume get(String uuid) {
        int len = size();
        for (int i = 0; i < len; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        throw new NoSuchElementException("Резюме не найдено");
    }

    void delete(String uuid) {
        int len = size();
        for (int i = 0; i < len; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, len - (i + 1));
                storage[len - 1] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] listResume = new Resume[size()];
        System.arraycopy(storage, 0, listResume, 0, size());
        return listResume;
    }

    int size() {
        int len = 0;
        for (Resume resume : storage) {
            if (resume != null) len++;
        }
        return len;
    }
}

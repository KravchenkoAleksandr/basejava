import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayStorage {
    private static final int SUM_NUM_RESUME = 10000;
    Resume[] storage = new Resume[SUM_NUM_RESUME];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size < SUM_NUM_RESUME && !isExists(resume.uuid)) {
            storage[size++] = resume;
            return;
        }
        String noRoom = "Места для добавления резюме недостаточно";
        String elementAlreadyExists = "Резюме " + resume.uuid + " существует";
        System.out.println(isExists(resume.uuid) ? elementAlreadyExists : noRoom);
    }

    Resume get(String uuid) {
        if (isExists(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        }
        throw new NoSuchElementException("Резюме " + uuid + " не найдено");
    }


    void delete(String uuid) {
        if (isExists(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
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

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    void update(Resume resume) {
        if (isExists(resume.uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i] == resume) {
                    storage[i] = resume;
                    return;
                }
            }
        }
        System.out.println("Резюме " + resume.uuid + " не найдено для обновления");
    }

    private boolean isExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) return true;
        }
        return false;
    }
}
import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size >= storage.length) {
            storage = Arrays.copyOf(storage, storage.length * 2);
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        return index == -1 ? null : storage[index];
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("No item for delete");
        } else {
            storage[index] = storage[size - 1];
            size--;
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = Arrays.copyOf(storage, size);
        return allResumes;
        }

    int size() {
        return size;
    }

    /**
     * @return the index of the element or -1 if the element does not exist
     */
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].uuid, uuid)) {
                return i;
            }
        }
        return -1;
    }
}

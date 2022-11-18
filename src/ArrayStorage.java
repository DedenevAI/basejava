import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
    }

    void save(Resume r) {
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
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

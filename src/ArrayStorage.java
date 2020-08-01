/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size += 1;
    }

    Resume get(String uuid) {
        int idx = 0;
        if (size == 0) return null;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                idx = i;
                break;
            } else return null;
        }
        return storage[idx];
    }


    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                for (int j = i; j < size; j++) {
                    if (storage[j + 1] != null) {
                        storage[j] = storage[j + 1];
                    }
                }
                storage[size - 1] = null;
                size -= 1;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[size];
        for (int i = 0; i < size; i++) {
            res[i] = storage[i];
        }
        return res;
    }

    int size() {
        return size;

    }
}
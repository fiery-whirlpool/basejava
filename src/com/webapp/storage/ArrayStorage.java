package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private final Resume[] storage = new Resume[10_000];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        int index = resumeLocate(r.getUuid());
        if (index < 0) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Хранилище переполненно!");
            }
        } else {
            System.out.println("Рузюме " + r.getUuid() + " уже есть в хранилище!");
        }
    }

    public Resume get(String uuid) {
        int index = resumeLocate(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Резюме " + uuid + " нет в хранилище!");
        return null;
    }


    public void delete(String uuid) {
        int index = resumeLocate(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме " + uuid + " нет в хранилище!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    public void update(Resume r) {
        int index = resumeLocate(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " обновлено!");
        } else {
            System.out.println("Резюме " + r.getUuid() + " нет в хранилище!");
        }
    }


    private int resumeLocate(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
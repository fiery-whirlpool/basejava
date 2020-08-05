package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private final Resume[] storage = new Resume[10000];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (size < storage.length) {
            if (!isAResume(r.getUuid())) {
                storage[size] = r;
                size++;
            } else System.out.println("Рузюме " + r.getUuid() + " уже есть в хранилище!");
        } else System.out.println("Хранилище переполненно!");
    }

    public Resume get(String uuid) {
        if (isAResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return storage[i];
                }
            }
        }
        System.out.println("Резюме " + uuid + " нет в хранилище!");
        return null;
    }


    public void delete(String uuid) {
        if (isAResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        } else {
            System.out.println("Резюме " + uuid + " нет в хранилище!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }

    public void update(Resume r) {
        if (isAResume(r.getUuid())) {
            System.out.println(r.getUuid());
        } else {
            System.out.println("Резюме " + r.getUuid() + " нет в хранилище!");
        }
    }

    private boolean isAResume(String uuide) {
        for (int i = 0; i < size; i++) {
            if (uuide.equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }
}
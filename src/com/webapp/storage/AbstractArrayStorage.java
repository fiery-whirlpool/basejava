package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected int size = 0;
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            if (size < STORAGE_LIMIT) {
                saveIndex(resume, index);
                size++;
            } else {
                System.out.println("Хранилище переполненно!");
            }
        } else {
            System.out.println("Рузюме " + resume.getUuid() + " уже есть в хранилище!");
        }
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено!");
        } else {
            System.out.println("Резюме " + resume.getUuid() + " нет в хранилище!");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Резюме " + uuid + " нет в хранилище!");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            delIndex(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме " + uuid + " нет в хранилище!");
        }
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);
    protected abstract void saveIndex(Resume resume, int index);
    protected abstract void delIndex(int index);

}

package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveIndex(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void delIndex(int index) {
        storage[index] = storage[size - 1];
    }
}
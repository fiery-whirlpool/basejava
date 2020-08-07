package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage,0,size,resume);
    }

    @Override
    protected void saveIndex(Resume resume, int index) {
        int indexNumber = -index -1;
        System.arraycopy(storage,indexNumber,storage,indexNumber + 1,size-indexNumber);
        storage[indexNumber] = resume;
    }

    @Override
    protected void delIndex(int index) {
        System.arraycopy(storage,index +1,storage, index, size - (index + 1));


    }


}

package com.webapp.storage;

import com.webapp.exception.NotExistStorageException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractArrayStorageTest {
    Storage storage;

    private static final String UUID1 = "uuid1";
    private static final Resume RESUME1 = new Resume(UUID1);

    private static final String UUID2 = "uuid2";
    private static final Resume RESUME2 = new Resume(UUID2);

    private static final String UUID3 = "uuid3";
    private static final Resume RESUME3 = new Resume(UUID3);

    private static final String UUID4 = "uuid4";
    private static final Resume RESUME4 = new Resume(UUID4);

    private static final String UUID5 = "uuid5";
    private static final Resume RESUME5 = new Resume(UUID5);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
        storage.save(RESUME4);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(RESUME1);
        Assert.assertEquals(RESUME1, storage.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME1, storage.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dumme");
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(RESUME1, resumes[0]);
        Assert.assertEquals(RESUME2, resumes[1]);
        Assert.assertEquals(RESUME3, resumes[2]);
        Assert.assertEquals(RESUME4, resumes[3]);
    }

    @Test
    public void size() {
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void save() {
        storage.clear();
        storage.save(RESUME1);
        Assert.assertNotNull(storage);
    }

    @Test
    public void saveExist() {
        storage.save(RESUME5);
        Assert.assertEquals(5, storage.size());
        Assert.assertEquals(RESUME5, storage.get(UUID5));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID4);
        storage.get(UUID4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("Dummy");
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("тест не пройден");
        }
        storage.save(new Resume());
    }
}
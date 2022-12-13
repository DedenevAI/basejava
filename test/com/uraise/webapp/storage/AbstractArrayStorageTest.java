package com.uraise.webapp.storage;

import com.uraise.webapp.exception.ExistStorageException;
import com.uraise.webapp.exception.NotExistStorageException;
import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.uraise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4);
    public static final String UUID_NOT_EXIST = "dummy";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Unexpected exception was thrown");
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertSame(RESUME_1, RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        assertSize(2);
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void noItemForDelete() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() {
        assertGet(new Resume("uuid1"));
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExists() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] getAll = storage.getAll();
        assertSize(expected.length);
        Assert.assertEquals(expected, getAll);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    private void assertSize(int expected) {
        Assert.assertEquals(expected, storage.size());
    }

    private void assertGet(Resume resumeForEquals) {
        Resume result = storage.get(resumeForEquals.getUuid());
        Assert.assertEquals(resumeForEquals, result);
    }
}
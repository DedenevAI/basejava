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

    @Before
    public void setUp(){
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void whenObjectAlreadyInTheStorageThrowAnException(){
        storage.save(RESUME_1);
    }
    @Test(expected = StorageException .class)
    public void whenStorageIsOverFlowThrowAnException(){
        try {
            for (int i = 4; i <= STORAGE_LIMIT; i++){
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Unexpected exception was thrown");
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
    }
    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        assertEqualsWithStorageSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void noItemForDelete() {
        storage.delete("uuid100");
    }

    @Test
    public void get() {
        Resume result = storage.get("uuid1");
        Assert.assertEquals(RESUME_1, result);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExists() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] result = storage.getAll();
        Assert.assertEquals(3, result.length);
    }

    @Test
    public void size() {
        assertEqualsWithStorageSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEqualsWithStorageSize(0);
    }

    private void assertEqualsWithStorageSize(int expected) {
        Assert.assertEquals(expected, storage.size());
    }
}
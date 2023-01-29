package com.uraise.webapp.storage;

import com.uraise.webapp.Config;
import com.uraise.webapp.exception.ExistStorageException;
import com.uraise.webapp.exception.NotExistStorageException;
import com.uraise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.uraise.webapp.model.ResumeTestData.createResume;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_1 = createResume(UUID_1, "fullName1");
    protected static final String UUID_2 = "uuid2";
    protected static final Resume RESUME_2 = createResume(UUID_2, "fullName2");
    protected static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_3 = createResume(UUID_3, "fullName3");
    protected static final String UUID_4 = "uuid4";
    protected static final Resume RESUME_4 = createResume(UUID_4, "fullName4");
    protected static final String UUID_NOT_EXIST = "dummy";

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(createResume(UUID_4, "fullName4"));
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertSame(RESUME_1, RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() {
        storage.update(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void noItemForDelete() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() {
        assertGet(createResume(UUID_1, "fullName1"));
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExists() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        List<Resume> getAll = storage.getAllSorted();
        Assert.assertEquals(3, getAll.size());
        Assert.assertEquals(getAll, Arrays.asList(createResume(RESUME_1.getUuid(), RESUME_1.getFullName()),
                createResume(RESUME_2.getUuid(), RESUME_2.getFullName()),
                createResume(RESUME_3.getUuid(), RESUME_3.getFullName())));
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

    private void assertGet(Resume resume) {
        Resume result = createResume(resume.getUuid(),resume.getFullName());
        Assert.assertEquals(resume, result);
    }
}

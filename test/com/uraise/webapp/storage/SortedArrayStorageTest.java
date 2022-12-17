package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import org.junit.Test;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
    @Test(expected = StorageException.class)
    public void saveOverflow() {
        overFlow();
    }

}
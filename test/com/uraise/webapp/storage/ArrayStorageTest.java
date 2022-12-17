package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
    @Test(expected = StorageException.class)
    public void saveOverflow() {
        overFlow();
    }
}
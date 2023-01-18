package com.uraise.webapp.storage;

import com.uraise.webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_PATH, new DataStreamSerializer()));
    }
}
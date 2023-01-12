package com.uraise.webapp.storage;

import com.uraise.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_PATH, new ObjectStreamSerializer()));
    }
}
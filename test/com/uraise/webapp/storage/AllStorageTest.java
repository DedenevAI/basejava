package com.uraise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AbstractStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageResumeTest.class,
        MapStorageUuidTest.class
})

public class AllStorageTest {
}

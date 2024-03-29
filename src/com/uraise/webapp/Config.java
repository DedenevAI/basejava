package com.uraise.webapp;

import com.uraise.webapp.storage.SqlStorage;
import com.uraise.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File(getHomeDir(),"config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private final File storageDir;
    private final Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }
    private static File getHomeDir(){
        String homeDir = System.getProperty("homeDir");
        File homeDirFile = new File(homeDir == null ? "." : homeDir);
        if (!homeDirFile.isDirectory()){
            throw new IllegalStateException(homeDir + " isn't directory");
        }
        return homeDirFile;
    }
}

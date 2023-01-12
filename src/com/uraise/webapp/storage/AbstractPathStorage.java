package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path>{
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)){
            throw new IllegalArgumentException(dir + " isn't directory or isn't writable");
        }
    }

    @Override
    protected boolean isExist(Path file) {
        return false;
    }

    @Override
    protected Path getSearchKey(String uuid) {
    return null;
    }

    @Override
    protected void updateResume(Path file, Resume r) {

    }

    @Override
    protected void doSave(Path file, Resume r) {
    }


    @Override
    protected void doDelete(Path file) {
    }

    @Override
    protected Resume getResume(Path file) {
        return null;
    }

    @Override
    protected List<Resume> copyAll() {
        return null;
    }

    @Override
    public void clear(){
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
    return 0;
    }


    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
}

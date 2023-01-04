package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "isn't directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "directory isn't readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(File file, Resume r) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }


    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<Resume> copyAll() {
        List<Resume> allResumes = new ArrayList<>();
        File[] files = listOfFiles(directory);
        for (File f : files) {
            try {
                allResumes.add(doRead(f));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return allResumes;
    }

    @Override
    public void clear() {
        File[] files = listOfFiles(directory);
        for (File f : files) {
            f.delete();
        }
    }

    @Override
    public int size() {
        File[] files = listOfFiles(directory);
        return files.length;
    }

    private File[] listOfFiles(File directory) {
        File[] files = directory.listFiles();
        Objects.requireNonNull(files, "directory is empty");
        return files;
    }
}

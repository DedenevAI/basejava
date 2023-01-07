package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

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
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateResume(file,r);
    }


    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> copyAll() {
        List<Resume> allResumes = new ArrayList<>();
        File[] files = listOfFiles(directory);
        for (File f : files) {
            allResumes.add(getResume(f));
        }
        return allResumes;
    }

    @Override
    public void clear() {
        File[] files = listOfFiles(directory);
        for (File f : files) {
            doDelete(f);
        }
    }

    @Override
    public int size() {
        File[] files = listOfFiles(directory);
        return files.length;
    }

    private File[] listOfFiles(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", directory.getName());
        }
        return files;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}

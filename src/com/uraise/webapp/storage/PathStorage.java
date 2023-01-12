package com.uraise.webapp.storage;

import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;
import com.uraise.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        this.streamSerializer = streamSerializer;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " isn't directory or isn't writable");
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void updateResume(Path path, Resume r) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Path path, Resume r) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + path, getFileName(path), e);
        }
        updateResume(path, r);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(path), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File read error", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> copyAll() {
        List<Resume> allResumes = new ArrayList<>();
        List<Path> pathList = listOfPaths();
        for (Path p : pathList) {
            allResumes.add(getResume(p));
        }
        return allResumes;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", e);
        }
    }

    @Override
    public int size() {
        return listOfPaths().size();
    }

    private List<Path> listOfPaths() {
        try {
            return Files.list(directory).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }
}

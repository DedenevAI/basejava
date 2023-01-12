package com.uraise.webapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MainFile {
    public static void main(String[] args) {
        Path directory = Paths.get("C:\\Users\\Alex\\Desktop\\basejava1");
        try {
            checkDirWithHierarchy(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //final String dir = "C:\\Users\\Alex\\Desktop\\basejava1";
        //checkDir(dir);

    }
    //implementation with visual hierarchy
    private static void checkDirWithHierarchy(Path mainDirectory) throws IOException {
        Files.walkFileTree(mainDirectory,new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                int count = dir.getNameCount() - mainDirectory.getNameCount() + 1;
                count += dir.getFileName().toString().length();
                String text = String.format("%" + count + "s", dir.getFileName());
                text = text.replaceAll("\\s", "-");
                System.out.println(text);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    //implementation without hierarchy
    private static void checkDir(String path) {
        File dir = new File(path);
        String[] files = dir.list();
        if (files != null) {
            for (String file : files) {
                File f = new File(path + File.separator + file);
                if (f.isFile()) {
                    System.out.println(f.getAbsolutePath());
                } else {
                    checkDir(path + File.separator + file);
                }
            }
        }
    }
}

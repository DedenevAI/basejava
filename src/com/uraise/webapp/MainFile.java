package com.uraise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        final String path = "C:\\Users\\Alex\\Desktop\\basejava1";
        checkDir(path);
    }

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

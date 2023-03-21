package com.myorg.hobbymat;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static void makeSurePathIsCreated(String path) throws IOException {
        File targetFile = new File(path);

        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        Path targetDirectory = targetFile.toPath();
        if (!targetDirectory.toFile().exists()) {
            Files.createDirectory(targetDirectory);
        }
    }

}

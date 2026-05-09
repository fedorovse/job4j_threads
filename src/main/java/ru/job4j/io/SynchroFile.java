package ru.job4j.io;

import java.io.File;

public class SynchroFile {
    private final File file;

    public SynchroFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }
}

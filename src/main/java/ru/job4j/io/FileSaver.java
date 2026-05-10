package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileSaver {
    public void saveContent(String content, SynchroFile file) throws IOException {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file.getFile()))) {
            out.write(content.getBytes());
            out.flush();
        }
    }
}

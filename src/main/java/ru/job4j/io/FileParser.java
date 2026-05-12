package ru.job4j.io;

import java.io.*;
import java.util.function.IntPredicate;

public class FileParser {
    private final File file;

    public FileParser(File file) {
        this.file = file;
    }

    public synchronized String getContent() throws IOException {
        return content(integer -> integer > 0);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return content(integer -> (integer > 0) && (integer < 0x80));
    }

    private String content(IntPredicate filter) throws IOException {

        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[1024];
            int data;
            while ((data = input.read(buffer)) != -1) {
                for (int i = 0; i < data; i++) {
                    if (filter.test(buffer[i])) {
                        sb.append((char) buffer[i]);
                    }
                }
            }
            return sb.toString();
        }
    }
}

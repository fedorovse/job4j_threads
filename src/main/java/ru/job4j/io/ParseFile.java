package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {

    public void saveContent(String content, SynchroFile file) throws IOException {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file.getFile()))) {
            out.write(content.getBytes());
            out.flush();
        }
    }

    public String content(Predicate<Integer> filter, SynchroFile file) throws IOException {

        try (InputStream input = new BufferedInputStream(new FileInputStream(file.getFile()))) {
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[1024];
            int data;
            while ((data = input.read(buffer)) != -1) {
                for (int i = 0; i < data; i++) {
                    if (filter.test(buffer[i] & 0xFF)) {
                        sb.append((char) buffer[i]);
                    }
                }
            }
            return sb.toString();
        }
    }
}

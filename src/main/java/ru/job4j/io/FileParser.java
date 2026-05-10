package ru.job4j.io;

import java.io.*;
import java.util.function.IntPredicate;

public class FileParser {

    public String getContent(SynchroFile file) throws IOException {
        return content(integer -> integer > 0, file);
    }

    public String getContentWithoutUnicode(SynchroFile file) throws IOException {
        return content(integer -> (integer > 0) && (integer < 0x80), file);
    }

    public String content(IntPredicate filter, SynchroFile file) throws IOException {

        try (InputStream input = new BufferedInputStream(new FileInputStream(file.getFile()))) {
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

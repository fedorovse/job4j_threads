package ru.job4j.thread;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;
    private final long ms = 1_000_000;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        long startAt = System.currentTimeMillis();
        File file = new File(fileName);

        System.out.println("Try to connect...");
        try (InputStream input = new URL(url).openStream();
             OutputStream output = new FileOutputStream(file)) {

            System.out.println("Open connection: " + (System.currentTimeMillis() - startAt) + " ms");

            byte[] dataBuffer = new byte[512];
            int bytesRead;
            int byteCount = 0;
            long readStart = System.nanoTime();
            while ((bytesRead = input.read(dataBuffer)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                long readEnd = System.nanoTime();
                byteCount += bytesRead;
                if (byteCount >= speed) {
                    long sleepTime = (readEnd - readStart) < ms ? ms - (readEnd - readStart) : 0L;
                    Thread.sleep(0, (int) sleepTime);
                    System.out.println("bytes: " + byteCount + " " + "nanos: " + (System.nanoTime() - readStart));
                    byteCount = 0;
                    readStart = System.nanoTime();
                }
            }
            output.flush();
            System.out.println(Files.size(file.toPath()) + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.out.println("Не заданы параметры");
            return;
        }
        String url = getUrl(args[0]);
        int speed = getSpeed(args[1], 1000);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }

    private static int getSpeed(String arg, int defaultSpeed) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return defaultSpeed;
        }
    }

    private static String getUrl(String url) {
        if (url == null || url.isEmpty()) {
            return "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        }
        return url;
    }
}
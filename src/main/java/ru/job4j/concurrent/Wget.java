package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            try {
                for (int i = 0; i <= 1000; i++) {
                    System.out.print("\rLoading : " + i  + "%");
                    Thread.sleep(100);
                }
                System.out.println(System.lineSeparator() + "Loaded.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        first.start();
    }
}

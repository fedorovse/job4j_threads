package ru.job4j;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        another.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        Thread second = new Thread(runnable);
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}

package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> { });
        Thread second = new Thread(() -> { });
        System.out.println("first " + first.getState());
        System.out.println("second " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
        || second.getState() != Thread.State.TERMINATED) {
            if (first.getState() != Thread.State.TERMINATED) {
                System.out.println("first " + first.getState());
            }

            if (second.getState() != Thread.State.TERMINATED) {
                System.out.println("second " + second.getState());
            }
        }
        System.out.println("first " + first.getState());
        System.out.println("second " + second.getState());
        System.out.println("Job is done!");
    }
}

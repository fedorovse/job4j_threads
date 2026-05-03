package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("interrupted in main");
        }
        progress.interrupt();
    }

    @Override
    public void run() {
        int count = 0;
        var process = new char[] {'-', '\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[count++]);
            count = count == process.length ? 0 : count;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(System.lineSeparator() + "interrupted in run");
                Thread.currentThread().interrupt();
            }
        }
    }
}

package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int count;

    public Count(int count) {
        this.count = count;
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void increment() {
        count++;
    }
}

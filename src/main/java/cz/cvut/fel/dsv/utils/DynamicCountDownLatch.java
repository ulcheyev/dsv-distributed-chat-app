package cz.cvut.fel.dsv.utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DynamicCountDownLatch {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int count;

    public DynamicCountDownLatch(int initialCount) {
        this.count = initialCount;
    }

    public void await() throws InterruptedException {
        lock.lock();
        try {
            while (count > 0) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void countDown() {
        lock.lock();
        try {
            if (count > 0) {
                count--;
                if (count == 0) {
                    condition.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void setCount(int newCount) {
        lock.lock();
        try {
            this.count = newCount;
            if (count == 0) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}

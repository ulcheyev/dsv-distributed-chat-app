package cz.cvut.fel.dsv.utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DsvConditionLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean conditionMet;

    public DsvConditionLock(boolean startVal) {
        this.conditionMet = startVal;
    }

    public void await(){
        lock.lock();
        try {
            while (!conditionMet) {
                try {
                    condition.await();
                }catch (InterruptedException e){
                    // ignored
                }
            }
        } finally {
            lock.unlock();
        }
    }


    public void lock(){
        conditionMet = false;
    }

    public void signal() {
        lock.lock();
        try {
            conditionMet = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

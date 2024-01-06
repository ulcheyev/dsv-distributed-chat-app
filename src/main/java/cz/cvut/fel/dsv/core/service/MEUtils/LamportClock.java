package cz.cvut.fel.dsv.core.service.MEUtils;

public class LamportClock {
    private int clock;

    public LamportClock() {
        clock = 1;
    }

    public synchronized int update() {
        return clock++;
    }

    public synchronized void receiveMsg(int time) {
        clock = Math.max(time, clock) + 1;
    }

    public synchronized int getClock() {
        return clock;
    }

    public boolean isLessThan(int other) {
        return this.clock < other;
    }

    public boolean isEqual(int other) {
        return this.clock == other;
    }

    public String toString() {
        return Integer.toString(clock);
    }
}

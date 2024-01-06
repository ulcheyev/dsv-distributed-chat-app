package cz.cvut.fel.dsv.core.service.MEUtils;

public class TimesTamp {

    private int clock;

    public TimesTamp(){
        clock=1;
    }

    public synchronized int update(){
        return clock++;
    }

    public synchronized void setClock(TimesTamp other){
        this.clock = other.clock;
    }

    public synchronized void receiveMsg(int time){
        clock=Math.max(time, clock)+1;
    }

    public synchronized int getClock(){
        return clock;
    }

    public synchronized boolean isLessThan(int other){
        return this.clock < other;
    }

    public synchronized boolean isEqual(int other) {
        return this.clock == other;
    }

    public String toString(){
        return Integer.toString(clock);
    }

}

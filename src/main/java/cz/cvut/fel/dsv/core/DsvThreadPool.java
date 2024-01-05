package cz.cvut.fel.dsv.core;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class DsvThreadPool implements Executor {
    @Getter
    private final Executor pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    @Setter
    private static DsvThreadPool INSTANCE;

    public static DsvThreadPool getInstance() {
        if(INSTANCE == null) {
            synchronized (DsvThreadPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DsvThreadPool();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void execute(Runnable runn) {
       pool.execute(runn);
    }



}

package cz.cvut.fel.dsv.core;

import lombok.Getter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DsvThreadPool {
    @Getter private static final Executor pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private DsvThreadPool() {
    }

    public static void execute(Runnable runn) { pool.execute(runn); }


}

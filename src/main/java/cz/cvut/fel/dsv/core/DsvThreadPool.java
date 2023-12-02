package cz.cvut.fel.dsv.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DsvThreadPool {
    private static Executor pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void execute(Runnable runn) { pool.execute(runn); }



}

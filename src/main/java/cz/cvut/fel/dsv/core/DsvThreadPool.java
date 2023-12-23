package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.utils.DsvLogger;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_YELLOW_TH_POOL;

public class DsvThreadPool implements Executor {
    @Getter
    private final Executor pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Logger logger = DsvLogger.getLogger("THREAD POOl", ANSI_YELLOW_TH_POOL, DsvThreadPool.class);
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

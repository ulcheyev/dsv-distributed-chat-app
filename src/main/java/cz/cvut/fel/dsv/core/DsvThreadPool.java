package cz.cvut.fel.dsv.core;

import cz.cvut.fel.dsv.core.data.NodeState;
import cz.cvut.fel.dsv.utils.DsvLogger;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_YELLOW_TH_POOL;

public class DsvThreadPool {
    @Getter private static final Executor pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Logger logger = DsvLogger.getLogger("THREAD POOl", ANSI_YELLOW_TH_POOL, DsvThreadPool.class);
    private static final Node node = Node.getInstance();
    private static Queue<Runnable> delayedExecutors;

    private DsvThreadPool() {
        delayedExecutors = new LinkedList<>();
    }

    public static void execute(Runnable runn) {
        pool.execute(runn);
    }
}

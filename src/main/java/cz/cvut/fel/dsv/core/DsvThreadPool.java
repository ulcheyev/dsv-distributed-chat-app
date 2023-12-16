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

import static cz.cvut.fel.dsv.core.Config.ANSI_YELLOW_TH_POOL;

public class DsvThreadPool {
    @Getter
    private static final Executor pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Logger logger = DsvLogger.getLogger("THREAD POOl", ANSI_YELLOW_TH_POOL, DsvThreadPool.class);
    @Setter
    private static Node node;
    private static DsvThreadPool INSTANCE;
    private static Queue<Runnable> delayedExecutors;

    private DsvThreadPool() {
        delayedExecutors = new LinkedList<>();
    }

    public static DsvThreadPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DsvThreadPool();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void execute(Runnable runn) {
        pool.execute(runn);
    }

    public void blockingExecute(Runnable runn) {
        String req = ((Thread) runn).getName();
        if (node.getState() != NodeState.RELEASED) {
            logger.info("[Blocking executor] request " + req + " is delayed. Queue size = " + delayedExecutors.size());
            delayedExecutors.add(runn);
        } else {
            node.setState(NodeState.BUSY);
            logger.info("[Blocking executor] request " + req + " is processing. Queue size = " + delayedExecutors.size());
            execute(runn);
        }
    }

    public void notifyExecutors() {
        if (!delayedExecutors.isEmpty()) {
            Runnable removed = delayedExecutors.remove();
            blockingExecute(removed);
        }

    }
}

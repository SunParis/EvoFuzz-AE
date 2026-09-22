import java.util.concurrent.ThreadPoolExecutor;

public class TplClass5191 {

    private static final void method(java.util.concurrent.ThreadPoolExecutor pool, int n) throws Throwable {
        while (pool.getPoolSize() != n) Thread.yield();
    }
}


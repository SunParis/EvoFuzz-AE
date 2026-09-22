import java.util.concurrent.ThreadPoolExecutor;

public class TplClass5187 {

    private static final void method(java.util.concurrent.ThreadPoolExecutor pool, java.lang.Runnable r, int n) throws Throwable {
        for (int i = 0; i < 2 * n; i++) pool.execute(r);
    }
}


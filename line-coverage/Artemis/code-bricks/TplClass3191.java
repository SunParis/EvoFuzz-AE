import java.util.concurrent.atomic.AtomicBoolean;

public class TplClass3191 {

    private static final void method(java.util.concurrent.atomic.AtomicBoolean guard1) throws Throwable {
        // relaxed memory order
        while (!guard1.weakCompareAndSet(false, true)) ;
    }
}


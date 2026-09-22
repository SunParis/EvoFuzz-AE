import java.util.concurrent.atomic.AtomicBoolean;

public class TplClass3193 {

    private static final void method(java.util.concurrent.atomic.AtomicBoolean guard2) throws Throwable {
        // relaxed memory order
        while (!guard2.weakCompareAndSet(false, true)) ;
    }
}


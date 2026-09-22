import java.util.concurrent.atomic.AtomicBoolean;

public class TplClass3190 {

    private static final void method(java.util.concurrent.atomic.AtomicBoolean guard1) throws Throwable {
        // busy-waiting
        while (!guard1.get()) ;
    }
}


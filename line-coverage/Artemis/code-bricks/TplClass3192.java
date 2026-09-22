import java.util.concurrent.atomic.AtomicBoolean;

public class TplClass3192 {

    private static final void method(java.util.concurrent.atomic.AtomicBoolean guard2) throws Throwable {
        // busy-waiting
        while (!guard2.get()) ;
    }
}


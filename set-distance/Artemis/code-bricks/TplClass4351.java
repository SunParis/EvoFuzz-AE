import java.nio.LongBuffer;

public class TplClass4351 {

    private static final void method(long longValue, java.nio.LongBuffer longBuf) throws Throwable {
        if (longBuf.put(2, longValue).get(2) != longValue) {
        }
    }
}


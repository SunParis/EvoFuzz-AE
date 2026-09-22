import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class TplClass2498 {

    private static final void method(int i, java.lang.reflect.Method m) throws Throwable {
        try {
            byte[] f = new byte[100000000];
            f[0] = (byte) i;
            f[1] = (byte) i;
            m.invoke(null, f, 0);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof OutOfMemoryError) {
                // Ignore, this is a stress test.
            } else {
            }
        } catch (OutOfMemoryError e) {
            // Ignore, this is a stress test.
        }
    }
}


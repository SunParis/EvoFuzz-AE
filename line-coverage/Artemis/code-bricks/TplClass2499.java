import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class TplClass2499 {

    private static final void method() throws Throwable {
        Class<?> c = Class.forName("java.lang.StringFactory");
        Method m = c.getDeclaredMethod("newStringFromBytes", byte[].class, int.class);
        // newStringFromBytes intrinsic.
        for (int i = 0; i < 10; i++) {
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
}


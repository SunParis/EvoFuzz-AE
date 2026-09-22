import java.lang.reflect.Method;

public class TplClass7236 {

    private static final void method(int failed) throws Throwable {
        try {
            Method m = new String[0].getClass().getMethod("clone", (Class<?>[]) null);
            failed++;
        } catch (NoSuchMethodException e) {
            // all good
            ;
        }
    }
}


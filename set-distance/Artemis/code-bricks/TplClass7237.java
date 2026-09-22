import java.lang.reflect.Method;

public class TplClass7237 {

    private static final void method(int failed) throws Throwable {
        try {
            Method m = new Object[0][0].getClass().getDeclaredMethod("clone", (Class<?>[]) null);
            failed++;
        } catch (NoSuchMethodException e) {
            // all good
            ;
        }
    }
}


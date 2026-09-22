import java.lang.reflect.Method;

public class TplClass7233 {

    private static final void method(java.lang.reflect.Method[] m, int failed) throws Throwable {
        for (Method mm : m) if (mm.getName().contentEquals("clone")) {
            failed++;
        }
    }
}


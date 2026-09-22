import java.lang.reflect.Method;

public class TplClass7240 {

    private static final void method(int failed) throws Throwable {
        Method[] m = new Integer[0][0][0].getClass().getMethods();
        for (Method mm : m) if (mm.getName().contentEquals("clone")) {
            failed++;
        }
    }
}


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TplClass7426 {

    private static final void method(java.lang.reflect.Method getInstanceMethod) throws Throwable {
        try {
            getInstanceMethod.invoke(null, null, "GB", "");
        } catch (InvocationTargetException exc) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof NullPointerException)) {
            }
        }
    }
}


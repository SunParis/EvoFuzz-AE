import java.util.Locale;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TplClass7429 {

    private static final void method() throws Throwable {
        // Locale.getInstance is not directly accessible.
        Method getInstanceMethod = Locale.class.getDeclaredMethod("getInstance", String.class, String.class, String.class);
        getInstanceMethod.setAccessible(true);
        getInstanceMethod.invoke(null, "null", "GB", "");
        try {
            getInstanceMethod.invoke(null, null, "GB", "");
        } catch (InvocationTargetException exc) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof NullPointerException)) {
            }
        }
        getInstanceMethod.invoke(null, "en", "null", "");
        try {
            getInstanceMethod.invoke(null, "en", null, "");
        } catch (InvocationTargetException exc) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof NullPointerException)) {
            }
        }
        getInstanceMethod.invoke(null, "en", "GB", "null");
        try {
            getInstanceMethod.invoke(null, "en", "GB", null);
        } catch (InvocationTargetException exc) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof NullPointerException)) {
            }
        }
    }
}


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TplClass2647 {

    private static final void method(int pidTemp, java.lang.reflect.Method killTemp, int sigquitTemp) throws Throwable {
        try {
            Class<?> osClass = Class.forName("android.system.Os");
            Method getpid = osClass.getDeclaredMethod("getpid");
            pidTemp = (Integer) getpid.invoke(null);
            Class<?> osConstants = Class.forName("android.system.OsConstants");
            Field sigquitField = osConstants.getDeclaredField("SIGQUIT");
            sigquitTemp = (Integer) sigquitField.get(null);
            killTemp = osClass.getDeclaredMethod("kill", int.class, int.class);
        } catch (Exception e) {
            if (!e.getClass().getName().equals("ErrnoException")) {
            }
        }
    }
}


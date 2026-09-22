import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TplClass2649 {

    private static final void method(int sigquit, int pid, java.lang.reflect.Method kill) throws Throwable {
        int pidTemp = -1;
        int sigquitTemp = -1;
        Method killTemp = null;
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
        pid = pidTemp;
        sigquit = sigquitTemp;
        kill = killTemp;
    }
}


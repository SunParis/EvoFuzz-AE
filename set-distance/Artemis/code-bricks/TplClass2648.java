import java.lang.reflect.Method;

public class TplClass2648 {

    private static final void method(int pid, int sigquit, java.lang.reflect.Method kill) throws Throwable {
        try {
            kill.invoke(null, pid, sigquit);
        } catch (Exception e) {
            if (!e.getClass().getName().equals("ErrnoException")) {
            }
        }
    }
}


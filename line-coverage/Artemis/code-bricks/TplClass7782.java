import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class TplClass7782 {

    private static final void method(java.lang.reflect.Method m) throws Throwable {
        try {
            m.invoke(null, null);
        } catch (AbstractMethodError e) {
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (!(t instanceof AbstractMethodError)) {
            }
        } catch (Throwable t) {
        }
    }
}


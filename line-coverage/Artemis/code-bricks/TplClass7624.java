import java.lang.reflect.Field;

public class TplClass7624 {

    private static final void method(java.lang.Throwable t, java.lang.Throwable cause) throws Throwable {
        Field f = Throwable.class.getDeclaredField("cause");
        f.setAccessible(true);
        f.set(t, cause);
    }
}


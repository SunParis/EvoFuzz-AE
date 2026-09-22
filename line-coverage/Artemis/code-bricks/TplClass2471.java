import java.lang.reflect.Method;

public class TplClass2471 {

    private static final void method() throws Throwable {
        try {
            Class<?> mirandaClass = Class.forName("MirandaAbstract");
            Method mirandaMethod = mirandaClass.getDeclaredMethod("inInterface");
        } catch (NoSuchMethodException nsme) {
        } catch (Exception e) {
        }
    }
}


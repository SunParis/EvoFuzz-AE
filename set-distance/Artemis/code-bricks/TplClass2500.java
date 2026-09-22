import java.lang.reflect.Method;

public class TplClass2500 {

    private static final void method(int i, java.lang.reflect.Method m) throws Throwable {
        byte[] f = new byte[100000000];
        f[0] = (byte) i;
        f[1] = (byte) i;
        m.invoke(null, f, 0);
    }
}


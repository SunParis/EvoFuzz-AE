import java.lang.invoke.MethodHandle;

public class TplClass105 {

    private static final void method(java.lang.invoke.MethodHandle test, int thrown) throws Throwable {
        thrown++;
        test.invokeExact(0, 1, 2, 3, 4);
    }
}


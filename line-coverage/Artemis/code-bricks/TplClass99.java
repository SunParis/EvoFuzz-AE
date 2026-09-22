import java.lang.invoke.MethodHandle;
import java.lang.invoke.WrongMethodTypeException;

public class TplClass99 {

    private static final void method(java.lang.invoke.MethodHandle test, int thrown) throws Throwable {
        try {
            thrown++;
            test.invokeExact(0, 1, 2, 3, 4);
        } catch (WrongMethodTypeException wmt) {
            if (wmt.getStackTrace().length < 1)
                ;
        }
    }
}


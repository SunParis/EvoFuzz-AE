import java.lang.invoke.MethodHandle;
import java.lang.invoke.WrongMethodTypeException;

public class TplClass97 {

    private static final void method(java.lang.invoke.MethodHandle test, int thrown) throws Throwable {
        try {
            test.invokeExact(0, 1, 2);
        } catch (WrongMethodTypeException wmt) {
            thrown++;
            if (wmt.getStackTrace().length < 1)
                ;
        }
    }
}


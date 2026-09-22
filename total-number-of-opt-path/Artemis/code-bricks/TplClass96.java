import java.lang.invoke.MethodHandle;
import java.lang.invoke.WrongMethodTypeException;

public class TplClass96 {

    private static final void method(java.lang.invoke.MethodHandle test, int thrown) throws Throwable {
        try {
            test.invokeExact(0, 1);
        } catch (WrongMethodTypeException wmt) {
            thrown++;
            if (wmt.getStackTrace().length < 1)
                ;
        }
    }
}


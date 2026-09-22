import java.lang.invoke.WrongMethodTypeException;

public class TplClass101 {

    private static final void method(int thrown, java.lang.invoke.WrongMethodTypeException wmt) throws Throwable {
        thrown++;
        if (wmt.getStackTrace().length < 1)
            ;
    }
}


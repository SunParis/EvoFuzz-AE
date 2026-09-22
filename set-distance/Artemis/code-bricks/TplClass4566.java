import java.lang.reflect.Array;

public class TplClass4566 {

    private static final void method(java.lang.Object strArray) throws Throwable {
        try {
            Array.set(strArray, 0, new Integer(5));
        } catch (IllegalArgumentException iae) {
        }
    }
}


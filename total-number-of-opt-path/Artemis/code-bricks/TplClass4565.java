import java.lang.reflect.Array;

public class TplClass4565 {

    private static final void method(java.lang.Object strArray) throws Throwable {
        try {
            Array.set(strArray, 2, "entry two");
        } catch (ArrayIndexOutOfBoundsException abe) {
        }
    }
}


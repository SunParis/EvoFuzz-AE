import java.lang.reflect.Array;

public class TplClass4561 {

    private static final void method(java.lang.Object charArray, char[] array) throws Throwable {
        try {
            for (int i = 0; i < array.length; i++) {
                if (Array.getInt(charArray, i) - '0' != i) {
                }
            }
            if (Array.getInt(charArray, 4) != '4') {
            }
        } catch (IllegalArgumentException iae) {
        }
    }
}


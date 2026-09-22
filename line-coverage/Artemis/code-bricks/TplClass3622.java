import java.util.Arrays;

public class TplClass3622 {

    private static final void method(java.lang.Object[] args, int i) throws Throwable {
        if (args[i] != null && args[i].getClass().isArray()) {
            Object array = args[i];
            if (array.getClass() == int[].class) {
            } else if (array.getClass() == long[].class) {
            } else if (array.getClass() == float[].class) {
            } else if (array.getClass() == double[].class) {
            } else {
            }
        } else {
        }
    }
}


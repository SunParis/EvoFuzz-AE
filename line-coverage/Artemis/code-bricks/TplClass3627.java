import java.util.Arrays;

public class TplClass3627 {

    private static final void method(java.lang.Object[] args, java.lang.String method) throws Throwable {
        for (int i = 0; i < args.length; ++i) {
            if (i != 0) {
            }
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
}


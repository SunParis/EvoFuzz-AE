import java.lang.reflect.Array;

public class TplClass4580 {

    private static final void method() throws Throwable {
        Object longArray = Array.newInstance(Long.TYPE, 2);
        Array.setInt(longArray, 0, 123);
        Array.setLong(longArray, 1, 0x1122334455667788L);
        try {
            Array.getInt(longArray, 0);
        } catch (IllegalArgumentException iae) {
        }
        long[] array = (long[]) longArray;
        if (array[0] != 123 || array[1] != 0x1122334455667788L) {
        }
        float f = Array.getFloat(longArray, 0);
        if (f < 122.9 || f > 123.1) {
        }
        if (Array.getLong(longArray, 1) != 0x1122334455667788L) {
        }
    }
}


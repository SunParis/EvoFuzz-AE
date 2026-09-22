import java.lang.reflect.Array;

public class TplClass4582 {

    private static final void method() throws Throwable {
        Object strArray;
        strArray = Array.newInstance(String.class, 2);
        String[] array = (String[]) strArray;
        array[0] = "entry zero";
        Array.set(strArray, 1, "entry one");
        try {
            Array.set(strArray, 2, "entry two");
        } catch (ArrayIndexOutOfBoundsException abe) {
        }
        if (!"entry zero".equals(Array.get(strArray, 0)))
            ;
        if (!"entry one".equals(array[1]))
            ;
        if (array.length != Array.getLength(strArray) || array.length != 2) {
        }
        try {
            Array.set(strArray, 0, new Integer(5));
        } catch (IllegalArgumentException iae) {
        }
    }
}


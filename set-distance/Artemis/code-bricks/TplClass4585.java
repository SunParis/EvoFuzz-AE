import java.lang.reflect.Array;

public class TplClass4585 {

    private static final void method() throws Throwable {
        Object intIntIntArray;
        int[] dimensions = { 3, 2, 1 };
        intIntIntArray = Array.newInstance(Integer.TYPE, dimensions);
        int[][][] array3 = (int[][][]) intIntIntArray;
        // trouble
        array3[0][0][0] = 123;
        array3[2][1][0] = 456;
        try {
            array3[2][1][1] = 768;
        } catch (ArrayIndexOutOfBoundsException abe) {
        }
    }
}


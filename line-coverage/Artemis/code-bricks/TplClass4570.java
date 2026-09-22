import java.lang.reflect.Array;

public class TplClass4570 {

    private static final void method() throws Throwable {
        Object intArray;
        intArray = Array.newInstance(Integer.TYPE, 2);
        int[] array = (int[]) intArray;
        array[0] = 5;
        Array.setInt(intArray, 1, 6);
        if (Array.getInt(intArray, 0) != 5)
            ;
        if (array[1] != 6)
            ;
        try {
            array[2] = 27;
        } catch (ArrayIndexOutOfBoundsException abe) {
        }
        try {
            Array.setInt(intArray, 2, 27);
        } catch (ArrayIndexOutOfBoundsException abe) {
        }
        if (array.length != Array.getLength(intArray) || array.length != 2) {
        }
        Integer x123 = Integer.valueOf(123);
        Integer x456 = Integer.valueOf(456);
        Array.set(intArray, 0, x123);
        Array.set(intArray, 1, x456);
        if (!Array.get(intArray, 0).equals(x123) || !Array.get(intArray, 1).equals(x456)) {
        }
        int[][] wrongArray;
        try {
            wrongArray = (int[][]) intArray;
        } catch (ClassCastException cce) {
        }
        intArray = Array.newInstance(Integer.TYPE, 0);
        if (Array.getLength(intArray) != 0)
            ;
    }
}


import java.lang.reflect.Array;

public class TplClass4587 {

    private static final void method() throws Throwable {
        Object strStrStrArray;
        int[] dimensions = { 1, 2, 3 };
        strStrStrArray = Array.newInstance(String.class, dimensions);
        String[][][] array3 = (String[][][]) strStrStrArray;
        array3[0][0][0] = "zero zero zero";
        array3[0][1][2] = "zero one two";
        try {
            array3[1][0][0] = "bad store";
        } catch (ArrayIndexOutOfBoundsException abe) {
        }
        try {
            String[][] array2 = (String[][]) strStrStrArray;
        } catch (ClassCastException cce) {
        }
        String[] strar = new String[4];
        strar[2] = "zero one two ++";
        array3[0][1] = strar;
        int[] dimensions2 = { 1, 2 };
        strStrStrArray = Array.newInstance(String[].class, dimensions2);
        array3 = (String[][][]) strStrStrArray;
        array3[0][1] = new String[3];
        array3[0][1][2] = "zero one two";
        try {
            array3[1][0][0] = "bad store";
        } catch (ArrayIndexOutOfBoundsException abe) {
        }
    }
}


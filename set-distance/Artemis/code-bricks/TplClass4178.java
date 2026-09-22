public class TplClass4178 {

    private static final void method(int result, int i, int[] array, int staticField) throws Throwable {
        // As a result, the invariant division on the field can be moved out of the loop.
        result += (staticField / 42) + Math.abs(array[i]);
    }
}


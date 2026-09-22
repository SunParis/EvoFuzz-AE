public class TplClass4169 {

    private static final void method(int result, int[] array, int staticField) throws Throwable {
        for (int i = 0; i < array.length; i++) {
            // An intrinsic call, unlike a general method call, cannot modify the field value.
            // As a result, the invariant division on the field can be moved out of the loop.
            result += (staticField / 42) + Math.abs(array[i]);
        }
    }
}


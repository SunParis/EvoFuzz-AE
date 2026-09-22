public class TplClass4244 {

    private static final void method(int result, int[] x) throws Throwable {
        // Infinite loop!
        for (int i = Integer.MIN_VALUE + 9, k = 0; i >= Integer.MIN_VALUE; i--) {
            result += x[k++];
        }
    }
}


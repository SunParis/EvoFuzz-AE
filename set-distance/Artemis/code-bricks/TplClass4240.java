public class TplClass4240 {

    private static final void method(int result, int[] x) throws Throwable {
        // Infinite loop!
        for (int i = Integer.MAX_VALUE - 9, k = 0; i <= Integer.MAX_VALUE; i++) {
            result += x[k++];
        }
    }
}


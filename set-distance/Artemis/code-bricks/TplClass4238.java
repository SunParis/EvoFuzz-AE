public class TplClass4238 {

    private static final void method(int result, int[] x) throws Throwable {
        for (int i = Integer.MAX_VALUE - 10; i < Integer.MAX_VALUE; i++) {
            result += x[i - Integer.MAX_VALUE + 10];
        }
    }
}


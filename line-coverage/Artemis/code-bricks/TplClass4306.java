public class TplClass4306 {

    private static final void method(int sResult) throws Throwable {
        int[] a = { 1 };
        for (int i = Integer.MIN_VALUE + 5; i <= Integer.MIN_VALUE + 10; i++) {
            for (int j = Integer.MIN_VALUE + 4; j < i - 5; j++) {
                sResult += a[j - (Integer.MIN_VALUE + 4)];
            }
        }
    }
}


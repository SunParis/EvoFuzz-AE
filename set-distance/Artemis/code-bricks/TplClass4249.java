public class TplClass4249 {

    private static final void method(int[] a, int sResult) throws Throwable {
        for (int i = Integer.MIN_VALUE + 5; i <= 10; i++) {
            for (int j = 4; j < i - 5; j++) {
                sResult += a[j - 4];
            }
        }
    }
}


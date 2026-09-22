public class TplClass4308 {

    private static final void method(int sResult) throws Throwable {
        int[] a = { 1 };
        for (int i = Integer.MIN_VALUE + 5; i <= 10; i++) {
            for (int j = 4; j < i - 5; j++) {
                sResult += a[j - 4];
            }
        }
    }
}


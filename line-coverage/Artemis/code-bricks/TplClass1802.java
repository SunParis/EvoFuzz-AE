public class TplClass1802 {

    private static final void method(int[] arr, int r) throws Throwable {
        for (int i = 0; i < 100; i++) {
            for (int j = i; j < 100; j++) {
                int a = 0;
                for (long k = 0; k < 100; k++) {
                    a += k;
                }
                if (arr != null)
                    a = arr[j];
                r += a;
            }
        }
    }
}


public class TplClass1803 {

    private static final void method(int[] arr, int i, int r) throws Throwable {
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


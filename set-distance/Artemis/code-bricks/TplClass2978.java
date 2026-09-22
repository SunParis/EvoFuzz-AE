public class TplClass2978 {

    private static final void method(int x, int[] a, int[] b) throws Throwable {
        for (int k = 0; k < 128; k++) {
            if (x > 100) {
                for (int j = 0; j < 128; j++) {
                    a[x]++;
                }
            } else {
                for (int i = 0; i < 128; i++) {
                    b[x]++;
                }
            }
        }
    }
}


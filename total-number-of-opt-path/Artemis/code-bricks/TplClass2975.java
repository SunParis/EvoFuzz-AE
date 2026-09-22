public class TplClass2975 {

    private static final void method(int x, int[] a, int[] b) throws Throwable {
        for (int k = 0; k < 16; k++) {
            for (int j = 0; j < 16; j++) {
                for (int i = 0; i < 128; i++) {
                    b[x]++;
                    a[i] = a[i] + 1;
                }
            }
        }
    }
}


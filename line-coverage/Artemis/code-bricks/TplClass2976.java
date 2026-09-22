public class TplClass2976 {

    private static final void method(int x, int[] a, int[] b) throws Throwable {
        for (int j = 0; j < 16; j++) {
            for (int i = 0; i < 128; i++) {
                b[x]++;
                a[i] = a[i] + 1;
            }
        }
    }
}


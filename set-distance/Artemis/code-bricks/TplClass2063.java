public class TplClass2063 {

    private static final void method(int[] a1, int[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = a1[i] + (i & 3);
        }
    }
}


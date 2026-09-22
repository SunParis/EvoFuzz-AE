public class TplClass4068 {

    private static final void method(int x, int[] a, int[] b, int[] c) throws Throwable {
        for (int i = 0; i < Math.min(Math.min(a.length, b.length), c.length); i++) {
            x += a[i] + b[i] + c[i];
        }
    }
}


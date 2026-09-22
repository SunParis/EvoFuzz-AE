public class TplClass4069 {

    private static final void method(int x, int[] a, int[] b, int[] c, int[] d) throws Throwable {
        for (int i = 0; i < Math.min(Math.min(a.length, b.length), Math.min(c.length, d.length)); i++) {
            x += a[i] + b[i] + c[i] + d[i];
        }
    }
}


public class TplClass2764 {

    private static final void method(int result, int[] x) throws Throwable {
        for (int i = x.length - 1; i >= 0; i--) {
            int k = i + 5;
            result += x[k - 5];
        }
    }
}


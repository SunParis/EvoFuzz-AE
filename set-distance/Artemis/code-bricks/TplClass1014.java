public class TplClass1014 {

    private static final void method(int[] src) throws Throwable {
        // left-shift the array
        for (int i = src.length - 1; i > 0; i--) {
            int tmp = src[i];
            src[i] = src[i - 1];
            src[i - 1] = tmp;
        }
    }
}


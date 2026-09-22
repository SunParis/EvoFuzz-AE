public class TplClass1015 {

    private static final void method(int[] src) throws Throwable {
        for (int i = 0; i < src.length; i++) {
            // correct value after shifting
            int value = (i - 1 + src.length) % src.length;
            if (src[i] != value) {
            }
        }
    }
}


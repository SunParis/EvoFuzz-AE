public class TplClass1019 {

    private static final void method(int[] ref, int[] dst, int[] src) throws Throwable {
        // initialize the arrays
        for (int i = 0; i < src.length; i++) {
            src[i] = i;
            // yes, dst[i] needed(otherwise src[i] will be replaced with i)
            dst[i] = 2;
            // src[i] depends on the store src[i]
            ref[i] = src[i];
        }
    }
}


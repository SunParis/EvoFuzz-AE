public class TplClass1022 {

    private static final void method(int i, int[] ref, int[] dst, int[] src) throws Throwable {
        src[i] = i;
        // yes, dst[i] needed(otherwise src[i] will be replaced with i)
        dst[i] = 2;
        // src[i] depends on the store src[i]
        ref[i] = src[i];
    }
}


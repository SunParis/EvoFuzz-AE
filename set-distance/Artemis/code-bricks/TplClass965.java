public class TplClass965 {

    private static final void method(int i, long l, boolean[] src) throws Throwable {
        for (int bit = 0; bit < 64; bit++) {
            src[i * 64 + bit] = (l & (1L << bit)) != 0;
        }
    }
}


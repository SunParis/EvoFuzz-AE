public class TplClass973 {

    private static final void method(int i, int bit, long l, boolean[] src) throws Throwable {
        src[i * 64 + bit] = (l & (1L << bit)) != 0;
    }
}


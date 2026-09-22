public class TplClass4651 {

    private static final void method(long x, long y) throws Throwable {
        for (long i = -64; i < 64; i++) {
            x = x & ~i;
            y = y | i;
        }
    }
}


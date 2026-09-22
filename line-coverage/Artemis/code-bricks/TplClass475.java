public class TplClass475 {

    private static final void method(long total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1L : 2L;
        }
    }
}


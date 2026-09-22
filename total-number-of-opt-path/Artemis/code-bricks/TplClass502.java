public class TplClass502 {

    private static final void method() throws Throwable {
        long total = 0;
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1L : 2L;
        }
    }
}


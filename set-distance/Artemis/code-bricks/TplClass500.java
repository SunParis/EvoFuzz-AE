public class TplClass500 {

    private static final void method() throws Throwable {
        long total = 0;
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 1.0f) ? 1L : 2L;
        }
    }
}


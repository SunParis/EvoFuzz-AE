public class TplClass474 {

    private static final void method(long total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 1.0f) ? 1L : 2L;
        }
    }
}


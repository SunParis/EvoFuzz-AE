public class TplClass472 {

    private static final void method(long total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += ((v >= 1 && v < 3) ? 1L : 2L);
        }
    }
}


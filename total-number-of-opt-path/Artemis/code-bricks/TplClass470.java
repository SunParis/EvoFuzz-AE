public class TplClass470 {

    private static final void method(int total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 1.0f) ? 1 : 2;
        }
    }
}


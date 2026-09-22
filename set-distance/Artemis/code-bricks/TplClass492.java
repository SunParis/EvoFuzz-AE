public class TplClass492 {

    private static final void method() throws Throwable {
        int total = 0;
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 1.0f) ? 1 : 2;
        }
    }
}


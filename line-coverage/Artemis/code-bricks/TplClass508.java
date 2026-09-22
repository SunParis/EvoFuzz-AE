public class TplClass508 {

    private static final void method() throws Throwable {
        float total = 0;
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 0.0f) ? 1.0f : 2.0f;
        }
    }
}


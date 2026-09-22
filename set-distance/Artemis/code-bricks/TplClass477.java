public class TplClass477 {

    private static final void method(float total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 0.0) ? 1.0f : 2.0f;
        }
    }
}


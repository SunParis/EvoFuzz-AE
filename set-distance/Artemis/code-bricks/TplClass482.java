public class TplClass482 {

    private static final void method(double total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 1.0f) ? 1.0d : 2.0d;
        }
    }
}


public class TplClass514 {

    private static final void method() throws Throwable {
        double total = 0;
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += (v > 1.0) ? 1.0d : 2.0d;
        }
    }
}


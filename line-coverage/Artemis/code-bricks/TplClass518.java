public class TplClass518 {

    private static final void method() throws Throwable {
        double total = 0;
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1.0d : 2.0d;
        }
    }
}


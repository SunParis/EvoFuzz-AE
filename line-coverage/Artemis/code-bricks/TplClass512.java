public class TplClass512 {

    private static final void method() throws Throwable {
        double total = 0;
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += ((v >= 1 && v < 3) ? 1.0d : 2.0d);
        }
    }
}


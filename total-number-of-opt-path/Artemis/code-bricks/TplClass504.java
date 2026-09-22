public class TplClass504 {

    private static final void method() throws Throwable {
        float total = 0;
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += ((v >= 1 && v < 3) ? 1.0f : 2.0f);
        }
    }
}


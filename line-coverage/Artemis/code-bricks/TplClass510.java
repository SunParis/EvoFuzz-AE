public class TplClass510 {

    private static final void method() throws Throwable {
        float total = 0;
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1.0f : 2.0f;
        }
    }
}


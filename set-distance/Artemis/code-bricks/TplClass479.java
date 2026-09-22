public class TplClass479 {

    private static final void method(float total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1.0f : 2.0f;
        }
    }
}


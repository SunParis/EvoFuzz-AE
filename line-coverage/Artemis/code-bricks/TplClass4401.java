public class TplClass4401 {

    private static final void method(int field, int iterations) throws Throwable {
        for (int i = 0; i < iterations; i++) {
            int a = field;
            field = a + i;
            int b = field;
            field = b + 2 * i;
        }
    }
}


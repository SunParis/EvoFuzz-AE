public class TplClass494 {

    private static final void method() throws Throwable {
        int total = 0;
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1 : 2;
        }
    }
}


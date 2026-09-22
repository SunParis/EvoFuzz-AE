public class TplClass471 {

    private static final void method(int total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1 : 2;
        }
    }
}


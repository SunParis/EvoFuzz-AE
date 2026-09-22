public class TplClass468 {

    private static final void method(int total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += ((v >= 1 && v < 3) ? 1 : 2);
        }
    }
}


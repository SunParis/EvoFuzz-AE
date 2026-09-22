public class TplClass483 {

    private static final void method(double total) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            total += (i % 4 != 0) ? 1.0d : 2.0d;
        }
    }
}


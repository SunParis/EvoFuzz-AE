public class TplClass524 {

    private static final void method() throws Throwable {
        Object a = new Object();
        Object b = new Object();
        ;
        int total = 0;
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += ((v > 1.0) ? a : b).hashCode();
        }
    }
}


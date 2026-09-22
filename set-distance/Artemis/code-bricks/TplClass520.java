public class TplClass520 {

    private static final void method() throws Throwable {
        Object a = new Object();
        Object b = new Object();
        ;
        int total = 0;
        for (int i = 0; i < 10000; i++) {
            total += ((i % 4 != 0) ? a : b).hashCode();
        }
    }
}


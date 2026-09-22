public class TplClass484 {

    private static final void method(java.lang.Object a, int total, java.lang.Object b) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            total += ((i % 4 != 0) ? a : b).hashCode();
        }
    }
}


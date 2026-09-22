public class TplClass487 {

    private static final void method(java.lang.Object a, int total, java.lang.Object b) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            int v = i % 4;
            total += ((v > 1.0f) ? a : b).hashCode();
        }
    }
}


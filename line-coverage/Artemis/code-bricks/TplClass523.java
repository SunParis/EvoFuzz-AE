public class TplClass523 {

    private static final void method(java.lang.Object a, int i, int total, java.lang.Object b) throws Throwable {
        int v = i % 4;
        total += ((v >= 1 && v < 3) ? a : b).hashCode();
    }
}


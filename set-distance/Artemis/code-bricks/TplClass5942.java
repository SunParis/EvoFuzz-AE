public class TplClass5942 {

    private static final void method(int lowestIndex, java.lang.Object[] a1, int x, int to, java.lang.Object lowest) throws Throwable {
        for (int y = x; y < to; y++) {
            if (((Comparable) a1[y]).compareTo((Comparable) lowest) < 0) {
                lowest = a1[y];
                lowestIndex = y;
            }
        }
    }
}


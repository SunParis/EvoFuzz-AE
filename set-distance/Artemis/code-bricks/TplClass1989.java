public class TplClass1989 {

    private static final void method(int iter, int i, int[] d, int size, int INITSIZE) throws Throwable {
        size = INITSIZE;
        for (i = 0; i < size; i++) {
            // 2
            d[0] = d[1];
            // 3
            d[1] = d[2];
            // 4
            d[2] = d[3];
            // 2
            d[3] = d[0];
            // 3
            d[0] = d[1];
            // 4
            d[1] = d[2];
            // 2
            d[2] = d[3];
            // 3
            d[3] = d[0];
            // 4
            d[0] = d[1];
            // 2
            d[1] = d[2];
            // 3
            d[2] = d[3];
            // 4
            d[3] = d[0];
            // 2
            d[0] = d[1];
            // 3
            d[1] = d[2];
            // 4
            d[2] = d[3];
            // 2
            d[3] = d[0];
            // 3
            d[0] = d[1];
            // 4
            d[1] = d[2];
            // 2
            d[2] = d[3];
            // 3
            d[3] = d[0];
            // 4
            d[0] = d[1];
            // 2
            d[1] = d[2];
            // 3
            d[2] = d[3];
            // 4
            d[3] = d[0];
            // 2
            d[0] = d[1];
            // 3
            d[1] = d[2];
            // 4
            d[2] = d[3];
            // 2
            d[3] = d[0];
            // 3
            d[0] = d[1];
            // 4
            d[1] = d[2];
            // 2
            d[2] = d[3];
            // 3
            d[3] = d[0];
        }
        // try to defeat dead code elimination
        if (d[0] == d[1]) {
        }
    }
}


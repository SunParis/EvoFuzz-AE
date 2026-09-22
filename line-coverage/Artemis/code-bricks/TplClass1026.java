public class TplClass1026 {

    private static final void method() throws Throwable {
        int k = 1 << 31;
        for (int j = 0; j < 1000000; j++) {
            int i = -1;
            while (i < 10) {
                i++;
            }
            int m = k * i;
            int[] O1 = new int[20];
            int[] O2 = new int[20];
            // will crash on amd64
            System.arraycopy(O1, i, O2, i, 1);
            // will crash on sparcv9
            System.arraycopy(O1, m, O2, m, 1);
        }
    }
}


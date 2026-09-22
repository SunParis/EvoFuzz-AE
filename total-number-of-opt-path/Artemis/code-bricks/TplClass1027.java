public class TplClass1027 {

    private static final void method(int k) throws Throwable {
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


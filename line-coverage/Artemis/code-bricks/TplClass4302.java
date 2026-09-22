public class TplClass4302 {

    private static final void method(int sResult) throws Throwable {
        int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int i = 0;
        // OOB!
        do {
            sResult += x[i++];
        } while (i <= x.length);
    }
}


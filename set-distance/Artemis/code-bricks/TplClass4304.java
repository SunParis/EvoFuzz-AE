public class TplClass4304 {

    private static final void method(int sResult) throws Throwable {
        int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int i = x.length - 1;
        // OOB!
        do {
            sResult += x[i--];
        } while (-1 <= i);
    }
}


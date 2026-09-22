public class TplClass4286 {

    private static final void method(int[] x, int i, int sResult) throws Throwable {
        // OOB!
        do {
            sResult += x[i++];
        } while (i <= x.length);
    }
}


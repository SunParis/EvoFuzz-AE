public class TplClass4246 {

    private static final void method(int[] x, int sResult) throws Throwable {
        // OOB!
        for (int i = 0; i <= x.length; i++) {
            sResult += x[i];
        }
    }
}


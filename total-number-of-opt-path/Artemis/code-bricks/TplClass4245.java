public class TplClass4245 {

    private static final void method(int[] x, int sResult) throws Throwable {
        // OOB!
        for (int i = -1; i < x.length; i++) {
            sResult += x[i];
        }
    }
}


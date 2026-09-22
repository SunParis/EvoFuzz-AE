public class TplClass4287 {

    private static final void method(int[] x, int i, int sResult) throws Throwable {
        // OOB!
        do {
            sResult += x[i--];
        } while (-1 <= i);
    }
}


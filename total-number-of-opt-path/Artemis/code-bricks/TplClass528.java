public class TplClass528 {

    private static final void method(int i, float f, int n) throws Throwable {
        try {
            // instruction that can trap
            int x = 1 / n;
            f = 2;
            i = 2;
            // instruction that can trap
            int y = 2 / n;
        } catch (Exception ex) {
            f++;
            i++;
        }
    }
}


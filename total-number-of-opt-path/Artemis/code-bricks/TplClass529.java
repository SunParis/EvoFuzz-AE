public class TplClass529 {

    private static final void method(int n) throws Throwable {
        float f = 1;
        int i = 1;
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


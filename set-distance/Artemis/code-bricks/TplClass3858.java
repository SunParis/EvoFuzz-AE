public class TplClass3858 {

    private static final void method(char[] x) throws Throwable {
        // Basically a nop due to zero extension.
        for (int i = 0; i < x.length; i++) {
            x[i] = (char) Math.abs(x[i]);
        }
    }
}


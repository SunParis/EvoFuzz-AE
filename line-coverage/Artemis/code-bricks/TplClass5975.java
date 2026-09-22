public class TplClass5975 {

    private static final void method() throws Throwable {
        Process p = Runtime.getRuntime().exec("false");
        int s = p.waitFor();
        if (s != 1) {
        }
    }
}


public class TplClass5973 {

    private static final void method(int N) throws Throwable {
        for (int i = 0; i < N; i++) {
            Process p = Runtime.getRuntime().exec("false");
            int s = p.waitFor();
            if (s != 1) {
            }
        }
    }
}


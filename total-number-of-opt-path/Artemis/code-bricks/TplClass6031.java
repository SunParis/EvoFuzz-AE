public class TplClass6031 {

    private static final void method(java.lang.String ECHO, int RUNS) throws Throwable {
        for (int i = 0; i <= RUNS; i++) {
            Process process = Runtime.getRuntime().exec(ECHO + " x");
            process.destroy();
        }
    }
}


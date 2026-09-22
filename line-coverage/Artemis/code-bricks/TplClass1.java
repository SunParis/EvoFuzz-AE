public class TplClass1 {

    private static final void method(int[][] ar, int a, int b, int passed) throws Throwable {
        try {
            ar = new int[a][b];
        } catch (ThreadDeath e) {
            passed++;
        }
    }
}


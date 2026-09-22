public class TplClass1029 {

    private static final void method() throws Throwable {
        // Run long enough to induce an OSR
        for (int i = 0; i < 10000000; i++) {
        }
    }
}


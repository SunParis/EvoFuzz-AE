public class TplClass6605 {

    private static final void method(int threadId, java.lang.Throwable ex, int INITIAL_VALUE, java.lang.Throwable[] exceptions) throws Throwable {
        exceptions[threadId - INITIAL_VALUE] = ex;
    }
}


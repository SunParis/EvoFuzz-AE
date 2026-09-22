public class TplClass2611 {

    private static final void method(boolean incoming) throws Throwable {
        // 'incoming' must have a use at both back edges.
        for (long i = System.nanoTime(); i < 42; ++i) {
            for (long j = System.currentTimeMillis(); j != 42; ++j) {
            }
        }
    }
}


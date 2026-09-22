public class TplClass2613 {

    private static final void method(boolean incoming) throws Throwable {
        // 'incoming' must have a use only at the first loop's back edge.
        for (long i = System.nanoTime(); i < 42; ++i) {
            for (long j = System.currentTimeMillis(); j != 42; ++j) {
            }
        }
    }
}


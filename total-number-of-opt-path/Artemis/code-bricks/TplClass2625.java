public class TplClass2625 {

    private static final void method(boolean field) throws Throwable {
        // 'incoming' must have a use at both back edges.
        boolean incoming = field;
        while (Runtime.getRuntime() != null) {
            // beat pre-header creation
            System.nanoTime();
            while (incoming) {
            }
            // beat back edge splitting
            System.nanoTime();
        }
    }
}


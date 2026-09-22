public class TplClass5420 {

    private static final void method(java.lang.Thread tdThread) throws Throwable {
        // Wait for mean event to do the deed and thread to die.
        try {
            do {
                Thread.sleep(100);
            } while (tdThread == null);
        } catch (InterruptedException e) {
        }
    }
}


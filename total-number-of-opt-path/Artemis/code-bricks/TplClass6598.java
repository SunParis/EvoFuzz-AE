public class TplClass6598 {

    private static final void method(java.lang.Thread child) throws Throwable {
        // Wait for child (if any)
        if (child != null) {
            try {
                child.join();
            } catch (InterruptedException e) {
            }
        }
    }
}


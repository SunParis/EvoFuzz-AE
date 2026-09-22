public class TplClass538 {

    private static final void method(long DIVISOR) throws Throwable {
        long value = 0;
        try {
            value = Long.decode(System.getProperty("divisor"));
        } catch (Throwable t) {
            // This one is required for the Class.forName() in main.
        }
        DIVISOR = value;
    }
}


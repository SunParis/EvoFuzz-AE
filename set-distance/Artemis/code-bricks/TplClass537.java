public class TplClass537 {

    private static final void method(long value) throws Throwable {
        try {
            value = Long.decode(System.getProperty("divisor"));
        } catch (Throwable t) {
            // This one is required for the Class.forName() in main.
        }
    }
}


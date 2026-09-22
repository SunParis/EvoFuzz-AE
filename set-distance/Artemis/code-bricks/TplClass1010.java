public class TplClass1010 {

    private static final void method(long DIVISOR) throws Throwable {
        long value = 0;
        try {
            value = Long.decode(System.getProperty("divisor"));
        } catch (Throwable e) {
        }
        DIVISOR = value;
    }
}


public class TplClass1051 {

    private static final void method(int testValue, int gap) throws Throwable {
        if ((testValue ^= 0x1) != 0) {
            gap = Long.numberOfTrailingZeros(testValue);
            testValue >>>= gap;
        }
    }
}


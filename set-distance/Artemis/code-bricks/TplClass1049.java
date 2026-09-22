public class TplClass1049 {

    private static final void method(int result, int testValue, int gap) throws Throwable {
        while (testValue != 0) {
            result++;
            if ((testValue ^= 0x1) != 0) {
                gap = Long.numberOfTrailingZeros(testValue);
                testValue >>>= gap;
            }
        }
    }
}


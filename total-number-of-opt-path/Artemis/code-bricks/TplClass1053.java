public class TplClass1053 {

    private static final void method(int result, int testValue) throws Throwable {
        int gap = Long.numberOfTrailingZeros(testValue);
        testValue >>>= gap;
        while (testValue != 0) {
            result++;
            if ((testValue ^= 0x1) != 0) {
                gap = Long.numberOfTrailingZeros(testValue);
                testValue >>>= gap;
            }
        }
    }
}


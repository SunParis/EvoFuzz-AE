public class TplClass1055 {

    private static final void method(int testValue, int gap) throws Throwable {
        gap = Long.numberOfTrailingZeros(testValue);
        testValue >>>= gap;
    }
}


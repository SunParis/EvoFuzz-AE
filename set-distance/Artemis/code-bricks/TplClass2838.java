public class TplClass2838 {

    private static final void method(int index, java.lang.Integer value) throws Throwable {
        try {
            Integer[] array = new Integer[2];
            // If we were to do optimization on the baseline register
            // allocator, generating code for the array set would fail on x86.
            array[index] = array[index + 1];
            array[index] = value;
        } catch (ArrayStoreException e) {
        }
    }
}


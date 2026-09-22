public class TplClass2839 {

    private static final void method(int index, java.lang.Integer value) throws Throwable {
        Integer[] array = new Integer[2];
        // allocator, generating code for the array set would fail on x86.
        array[index] = array[index + 1];
        array[index] = value;
    }
}


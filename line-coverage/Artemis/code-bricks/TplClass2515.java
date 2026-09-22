public class TplClass2515 {

    private static final void method(java.lang.Object[] result, int size) throws Throwable {
        try {
            result = new Object[size];
        } catch (OutOfMemoryError oome) {
            size /= 2;
        }
    }
}


public class TplClass2512 {

    private static final void method(java.lang.Object[] result, int size) throws Throwable {
        while (result == null && size != 0) {
            try {
                result = new Object[size];
            } catch (OutOfMemoryError oome) {
                size /= 2;
            }
        }
    }
}


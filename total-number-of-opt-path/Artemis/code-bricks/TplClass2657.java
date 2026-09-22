public class TplClass2657 {

    private static final void method(int index, int size, java.lang.Object[] data) throws Throwable {
        while (size > 0) {
            try {
                data[index] = new byte[size];
                index++;
            } catch (OutOfMemoryError e) {
                size /= 2;
            }
        }
    }
}


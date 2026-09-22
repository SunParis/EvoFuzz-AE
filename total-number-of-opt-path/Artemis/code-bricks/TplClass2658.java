public class TplClass2658 {

    private static final void method(int index, int size, java.lang.Object[] data) throws Throwable {
        try {
            data[index] = new byte[size];
            index++;
        } catch (OutOfMemoryError e) {
            size /= 2;
        }
    }
}


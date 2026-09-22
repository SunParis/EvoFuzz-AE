public class TplClass2511 {

    private static final void method(int index, int size, java.lang.Object[] data) throws Throwable {
        while (index != data.length && size != 0) {
            try {
                data[index] = new byte[size];
                ++index;
            } catch (OutOfMemoryError oome) {
                size /= 2;
            }
        }
    }
}


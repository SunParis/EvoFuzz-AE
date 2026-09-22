public class TplClass2514 {

    private static final void method(int index, int size, java.lang.Object[] data) throws Throwable {
        try {
            data[index] = new byte[size];
            ++index;
        } catch (OutOfMemoryError oome) {
            size /= 2;
        }
    }
}


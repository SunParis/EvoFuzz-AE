public class TplClass2506 {

    private static final void method(int index, java.lang.Object[] data, int size) throws Throwable {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
    }
}


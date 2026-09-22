public class TplClass3705 {

    private static final void method(int[] array) throws Throwable {
        for (int i = 0; i < array.length - 1; i++) {
            array[i + 1] = array[i] << array[i + 1];
        }
    }
}


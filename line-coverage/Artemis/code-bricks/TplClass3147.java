public class TplClass3147 {

    private static final void method(int[] array, int i) throws Throwable {
        if (array == null) {
            array = new int[5];
        } else {
            if (i == 5) {
                array = new int[10];
            }
            array[i] = i;
        }
    }
}


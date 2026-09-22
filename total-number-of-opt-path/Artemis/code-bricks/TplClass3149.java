public class TplClass3149 {

    private static final void method() throws Throwable {
        int[] array = null;
        for (int i = -1; i < 10; ++i) {
            if (array == null) {
                array = new int[5];
            } else {
                if (i == 5) {
                    array = new int[10];
                }
                array[i] = i;
            }
        }
        array.hashCode();
    }
}


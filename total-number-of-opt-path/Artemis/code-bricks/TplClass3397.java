public class TplClass3397 {

    private static final void method(int[] array2, int[] array1) throws Throwable {
        for (int j = 0; j < 100; j++) {
            int temp = 12345 * array1[j];
            array2[j] -= temp;
            array1[j] = temp;
        }
    }
}


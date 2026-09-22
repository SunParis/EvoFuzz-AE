public class TplClass4405 {

    private static final void method(int[] array, int i) throws Throwable {
        for (int j = 0; j < 100; j++) {
            // These two accesses MAY ALIAS
            array[i - 1]++;
            array[9 - i]++;
        }
    }
}


public class TplClass4419 {

    private static final void method(int i) throws Throwable {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int j = 0; j < 100; j++) {
            // These two accesses MAY ALIAS
            array[i - 1]++;
            array[9 - i]++;
        }
    }
}


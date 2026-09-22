public class TplClass4417 {

    private static final void method(int i) throws Throwable {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int j = 0; j < 100; j++) {
            array[i + 1]++;
            array[i + 2]++;
            array[i - 1]++;
        }
    }
}


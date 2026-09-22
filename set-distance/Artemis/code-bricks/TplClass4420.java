public class TplClass4420 {

    private static final void method(int[] array, int i) throws Throwable {
        // These two accesses MAY ALIAS
        array[i - 1]++;
        array[9 - i]++;
    }
}


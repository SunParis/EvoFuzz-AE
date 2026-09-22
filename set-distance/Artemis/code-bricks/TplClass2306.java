public class TplClass2306 {

    private static final void method(int[] a, int[] novec, int dead) throws Throwable {
        for (int i = 0; i < a.length; i++) {
            a[i] = novec[2 * i] + 3;
            // Increment value defined inside loop,
            // but sequence itself not used anywhere.
            dead += i;
        }
    }
}


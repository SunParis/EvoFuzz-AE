public class TplClass2367 {

    private static final void method(int[] a, int[] novec) throws Throwable {
        int dead = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = novec[2 * i] + 3;
            // Increment value defined inside loop,
            // but sequence itself not used anywhere.
            dead += i;
        }
    }
}


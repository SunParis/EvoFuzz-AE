public class TplClass2369 {

    private static final void method(int[] a, int[] novec, int k) throws Throwable {
        int dead = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = novec[2 * i] + 4;
            // Increment value of dead cycle may throw exception. Dynamic
            // BCE takes care of the bounds check though, which enables
            // removing the ArrayGet after removing the dead cycle.
            dead += a[k];
        }
    }
}


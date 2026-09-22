public class TplClass2307 {

    private static final void method(int[] a, int[] novec, int dead, int k) throws Throwable {
        for (int i = 0; i < a.length; i++) {
            a[i] = novec[2 * i] + 4;
            // Increment value of dead cycle may throw exception. Dynamic
            // BCE takes care of the bounds check though, which enables
            // removing the ArrayGet after removing the dead cycle.
            dead += a[k];
        }
    }
}


public class TplClass2368 {

    private static final void method(int[] a, int[] novec, int i, int dead) throws Throwable {
        a[i] = novec[2 * i] + 3;
        // but sequence itself not used anywhere.
        dead += i;
    }
}


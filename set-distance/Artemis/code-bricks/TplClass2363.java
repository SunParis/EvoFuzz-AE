public class TplClass2363 {

    private static final void method(int[] a, int[] novec) throws Throwable {
        int dead = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = novec[2 * i] + 1;
            dead += 5;
        }
    }
}


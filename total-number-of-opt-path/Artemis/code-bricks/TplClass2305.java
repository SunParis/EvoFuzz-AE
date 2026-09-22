public class TplClass2305 {

    private static final void method(int[] a, int[] novec, int dead1, int dead2, int dead3) throws Throwable {
        for (int i = 0; i < a.length; i++) {
            dead1 += 5;
            a[i] = novec[2 * i] + 2;
            dead2 += 10;
            dead3 += 100;
        }
    }
}


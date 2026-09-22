public class TplClass4272 {

    private static final void method(int[] a, int i) throws Throwable {
        for (int j = -3; j <= 3; j++) {
            // Range [-9,9]: unsafe.
            a[i * j] += 1;
        }
    }
}


public class TplClass4271 {

    private static final void method(int[] a) throws Throwable {
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                // Range [-9,9]: unsafe.
                a[i * j] += 1;
            }
        }
    }
}


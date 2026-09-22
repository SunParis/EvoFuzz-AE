public class TplClass4293 {

    private static final void method(int[] a) throws Throwable {
        try {
            for (int i = -3; i <= 3; i++) {
                for (int j = -3; j <= 3; j++) {
                    // Range [-9,9]: unsafe.
                    a[i * j] += 1;
                }
            }
        } catch (Exception e) {
            a[0] += 1000;
        }
    }
}


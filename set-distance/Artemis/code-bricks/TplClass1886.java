public class TplClass1886 {

    private static final void method(int max) throws Throwable {
        boolean test1 = false;
        boolean test2 = false;
        for (int i = 0; i < max; i++) {
            test1 = !test1;
        }
        for (int i = 0; i < max; i++) {
            test2 ^= true;
        }
        if (test1 != test2) {
        } else {
        }
    }
}


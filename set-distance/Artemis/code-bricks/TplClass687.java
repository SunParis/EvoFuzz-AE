public class TplClass687 {

    private static final void method(java.lang.Byte j2, java.lang.Byte j1) throws Throwable {
        for (int i = 0; i < 1000; i++) {
            if ((i & 1) == 0) {
                j1 = new Byte((byte) (j1 + 1));
            } else {
                j2 = new Byte((byte) (j2 + 2));
            }
        }
    }
}


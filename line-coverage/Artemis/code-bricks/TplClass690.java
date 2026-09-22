public class TplClass690 {

    private static final void method(java.lang.Byte j2, java.lang.Byte ibc, java.lang.Byte j1) throws Throwable {
        for (int i = 0; i < 1000; i++) {
            if ((i & 1) == 0) {
                j1 = (byte) (j1 + ibc);
            } else {
                j2 = (byte) (j2 + 2);
            }
        }
    }
}


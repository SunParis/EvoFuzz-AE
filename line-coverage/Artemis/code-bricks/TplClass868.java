public class TplClass868 {

    private static final void method(java.lang.Long j2, java.lang.Long j1) throws Throwable {
        for (int i = 0; i < 1000; i++) {
            if ((i & 1) == 0) {
                j1 = new Long(j1 + 1);
            } else {
                j2 = j2 + 2;
            }
        }
    }
}


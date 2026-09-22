public class TplClass1061 {

    private static final void method(java.lang.Object[] o) throws Throwable {
        int total = 0;
        try {
            // Exercise the implicit null check in the unverified entry point
            for (int i = 0; i < 40000; i++) {
                int limit = o.length;
                if (i < 20000)
                    limit = 1;
                for (int j = 0; j < limit; j++) {
                    total += o[j].hashCode();
                }
            }
        } catch (NullPointerException e) {
            // this is expected.  A true failure causes a crash
        }
    }
}


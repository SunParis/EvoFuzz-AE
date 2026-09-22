public class TplClass2743 {

    private static final void method(int i, java.lang.String test, java.lang.Object[] o) throws Throwable {
        try {
            for (; i < o.length; ++i) {
                o[i] = test.toCharArray();
            }
        } catch (OutOfMemoryError oom) {
            o = null;
        }
    }
}


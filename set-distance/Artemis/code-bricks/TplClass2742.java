public class TplClass2742 {

    private static final void method(int i, java.lang.Object[] o) throws Throwable {
        try {
            for (; i < o.length; ++i) o[i] = new char[1000000];
        } catch (OutOfMemoryError oom) {
        }
    }
}


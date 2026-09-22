public class TplClass3717 {

    private static final void method(java.lang.Object[] a) throws Throwable {
        for (int i = 0; i < 10; ++i) {
            a = new Object[i * 300000];
            Runtime.getRuntime().gc();
        }
    }
}


public class TplClass3718 {

    private static final void method(java.lang.Object[] a, int i) throws Throwable {
        a = new Object[i * 300000];
        Runtime.getRuntime().gc();
    }
}


public class TplClass7630 {

    private static final void method(int fail, java.lang.Class c, java.lang.Throwable t, java.lang.Throwable first) throws Throwable {
        String s = t.getClass().getName() + " constructor did not throw " + c.getName();
        if (first == null)
            first = new RuntimeException(s);
        fail++;
    }
}


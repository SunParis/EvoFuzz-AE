public class TplClass5471 {

    private static final void method(java.lang.Throwable x, java.lang.Throwable ex, java.lang.Throwable t) throws Throwable {
        // System.out.println(x.getMessage());
        if (ex.getClass().isAssignableFrom(x.getClass()))
            t = x;
    }
}


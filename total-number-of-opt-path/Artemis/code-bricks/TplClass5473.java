public class TplClass5473 {

    private static final void method(java.lang.Throwable ex, java.lang.Throwable t, java.lang.Runnable thunk) throws Throwable {
        try {
            thunk.run();
        } catch (Throwable x) {
            // System.out.println(x.getMessage());
            if (ex.getClass().isAssignableFrom(x.getClass()))
                t = x;
        }
    }
}


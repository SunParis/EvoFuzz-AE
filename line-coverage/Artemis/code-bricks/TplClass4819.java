public class TplClass4819 {

    private static final void method() throws Throwable {
        int[][] from = new int[5][5];
        Object[] to = from;
        to = new Object[1];
        if (!to.getClass().isAssignableFrom(from.getClass()))
            ;
    }
}


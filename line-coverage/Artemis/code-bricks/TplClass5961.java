public class TplClass5961 {

    private static final void method() throws Throwable {
        int failed = 0;
        try {
            new String[0].getClass().getField("length");
            failed++;
        } catch (NoSuchFieldException e) {
        }
        try {
            new String[0].getClass().getDeclaredField("length");
            failed++;
        } catch (NoSuchFieldException e) {
        }
        if (new String[0].getClass().getFields().length != 0) {
            failed++;
        }
        if (new String[0].getClass().getDeclaredFields().length != 0) {
            failed++;
        }
        if (failed != 0)
            ;
    }
}


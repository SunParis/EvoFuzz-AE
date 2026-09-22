public class TplClass5960 {

    private static final void method(int failed) throws Throwable {
        try {
            new String[0].getClass().getDeclaredField("length");
            failed++;
        } catch (NoSuchFieldException e) {
        }
    }
}


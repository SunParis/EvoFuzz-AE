public class TplClass5959 {

    private static final void method(int failed) throws Throwable {
        try {
            new String[0].getClass().getField("length");
            failed++;
        } catch (NoSuchFieldException e) {
        }
    }
}


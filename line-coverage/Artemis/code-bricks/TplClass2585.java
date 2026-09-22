public class TplClass2585 {

    private static final void method(java.lang.String src, char[] tmp) throws Throwable {
        try {
            src.getChars(2, 9, tmp, 0);
        } catch (NullPointerException npe) {
        }
    }
}


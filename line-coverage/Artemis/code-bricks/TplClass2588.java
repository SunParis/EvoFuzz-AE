public class TplClass2588 {

    private static final void method(char[] dst, java.lang.String src) throws Throwable {
        try {
            src.getChars(2, 1, dst, 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
        }
    }
}


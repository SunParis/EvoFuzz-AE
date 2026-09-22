public class TplClass2587 {

    private static final void method(char[] dst, java.lang.String src) throws Throwable {
        try {
            src.getChars(2, 19, dst, 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
        }
    }
}


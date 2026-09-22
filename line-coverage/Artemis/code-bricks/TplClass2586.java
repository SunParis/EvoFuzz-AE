public class TplClass2586 {

    private static final void method(char[] dst, java.lang.String src) throws Throwable {
        try {
            src.getChars(-1, 9, dst, 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
        }
    }
}


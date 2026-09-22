public class TplClass2589 {

    private static final void method(char[] dst, java.lang.String src) throws Throwable {
        try {
            src.getChars(2, 10, dst, 0);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        }
    }
}


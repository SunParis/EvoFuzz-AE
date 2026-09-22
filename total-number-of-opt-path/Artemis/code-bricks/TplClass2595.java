public class TplClass2595 {

    private static final void method() throws Throwable {
        String src = new String("Hello Android");
        char[] dst = new char[7];
        char[] tmp = null;
        try {
            src.getChars(2, 9, tmp, 0);
        } catch (NullPointerException npe) {
        }
        try {
            src.getChars(-1, 9, dst, 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
        }
        try {
            src.getChars(2, 19, dst, 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
        }
        try {
            src.getChars(2, 1, dst, 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
        }
        try {
            src.getChars(2, 10, dst, 0);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        }
        src.getChars(2, 9, dst, 0);
    }
}


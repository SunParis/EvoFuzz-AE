public class TplClass5759 {

    private static final void method() throws Throwable {
        boolean exceptionOccurred = false;
        try {
            new StringBuffer("abc").getChars(1, 0, new char[10], 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
            exceptionOccurred = true;
        }
        if (!exceptionOccurred) {
        }
    }
}


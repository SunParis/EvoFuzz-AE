public class TplClass5758 {

    private static final void method(boolean exceptionOccurred) throws Throwable {
        try {
            new StringBuffer("abc").getChars(1, 0, new char[10], 0);
        } catch (StringIndexOutOfBoundsException sioobe) {
            exceptionOccurred = true;
        }
    }
}


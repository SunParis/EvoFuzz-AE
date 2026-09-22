public class TplClass5786 {

    private static final void method(java.lang.StringBuffer sb) throws Throwable {
        try {
            sb.insert(0, new char[5], 1, Integer.MAX_VALUE);
        } catch (StringIndexOutOfBoundsException sobe) {
            // Test passed
        } catch (OutOfMemoryError oome) {
        }
    }
}


public class TplClass5788 {

    private static final void method() throws Throwable {
        StringBuffer sb = new StringBuffer("");
        StringBuffer sb1 = new StringBuffer("Some test StringBuffer");
        try {
            sb.insert(0, new char[5], 1, Integer.MAX_VALUE);
        } catch (StringIndexOutOfBoundsException sobe) {
            // Test passed
        } catch (OutOfMemoryError oome) {
        }
        try {
            sb1.insert(2, new char[25], 5, Integer.MAX_VALUE);
        } catch (StringIndexOutOfBoundsException sobe) {
            // Test passed
        } catch (ArrayIndexOutOfBoundsException aioe) {
        }
    }
}


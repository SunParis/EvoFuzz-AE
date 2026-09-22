public class TplClass5787 {

    private static final void method(java.lang.StringBuffer sb1) throws Throwable {
        try {
            sb1.insert(2, new char[25], 5, Integer.MAX_VALUE);
        } catch (StringIndexOutOfBoundsException sobe) {
            // Test passed
        } catch (ArrayIndexOutOfBoundsException aioe) {
        }
    }
}


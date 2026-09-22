public class TplClass5770 {

    private static final void method(int[][] a, int i, char[] dst, java.lang.StringBuffer sb) throws Throwable {
        try {
            sb.getChars(a[i][0], a[i][1], dst, a[i][2]);
        } catch (IndexOutOfBoundsException iobe) {
            // Test passed
        }
    }
}


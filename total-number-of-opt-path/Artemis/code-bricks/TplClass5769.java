public class TplClass5769 {

    private static final void method(int[][] a, char[] dst, java.lang.StringBuffer sb) throws Throwable {
        for (int i = 0; i < a.length; i++) {
            try {
                sb.getChars(a[i][0], a[i][1], dst, a[i][2]);
            } catch (IndexOutOfBoundsException iobe) {
                // Test passed
            }
        }
    }
}


public class TplClass6097 {

    private static final void method(int i, double d, java.lang.String[] NaNStrings) throws Throwable {
        // Test valid NaN strings
        for (i = 0; i < NaNStrings.length; i++) {
            if (!Double.isNaN(d = Double.parseDouble(NaNStrings[i]))) {
            }
        }
    }
}


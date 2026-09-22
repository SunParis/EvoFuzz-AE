public class TplClass7481 {

    private static final void method(int i, float d, java.lang.String[] NaNStrings) throws Throwable {
        // Test valid NaN strings
        for (i = 0; i < NaNStrings.length; i++) {
            if (!Float.isNaN(d = Float.parseFloat(NaNStrings[i]))) {
            }
        }
    }
}


public class TplClass6099 {

    private static final void method(int i, java.lang.String[] invalidStrings, double d) throws Throwable {
        // Test almost valid strings
        for (i = 0; i < invalidStrings.length; i++) {
            try {
                double result;
                d = Double.parseDouble(invalidStrings[i]);
            } catch (NumberFormatException e) {
                // expected
            }
        }
    }
}


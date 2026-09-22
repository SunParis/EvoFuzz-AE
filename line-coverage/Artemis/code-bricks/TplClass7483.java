public class TplClass7483 {

    private static final void method(int i, java.lang.String[] invalidStrings, float d) throws Throwable {
        // Test almost valid strings
        for (i = 0; i < invalidStrings.length; i++) {
            try {
                float result;
                d = Float.parseFloat(invalidStrings[i]);
            } catch (NumberFormatException e) {
                // expected
            }
        }
    }
}


import java.util.StringTokenizer;

public class TplClass6283 {

    private static final void method(java.lang.String delims, java.lang.String text, java.lang.String[] expected) throws Throwable {
        StringTokenizer tokenizer = new StringTokenizer(text, delims);
        int n = tokenizer.countTokens();
        if (n != expected.length) {
        }
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (!token.equals(expected[i++])) {
            }
        }
        if (i != expected.length) {
        }
    }
}


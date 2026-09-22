import java.util.StringTokenizer;

public class TplClass6279 {

    private static final void method(int i, java.lang.String[] expected, java.util.StringTokenizer tokenizer) throws Throwable {
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (!token.equals(expected[i++])) {
            }
        }
    }
}


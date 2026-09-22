public class TplClass3744 {

    private static final void method(int a, int b) throws Throwable {
        try {
            a = 1;
            // Would throw an ArithmeticException if b were null (hence
            // the enclosing `try' statement).
            int c = a % b;
        } finally {
        }
    }
}


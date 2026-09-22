public class TplClass3745 {

    private static final void method() throws Throwable {
        int a = 0;
        int b = 1;
        for (int i = 0; i < 3; i++) {
            try {
                a = 1;
                // Would throw an ArithmeticException if b were null (hence
                // the enclosing `try' statement).
                int c = a % b;
            } finally {
            }
        }
    }
}


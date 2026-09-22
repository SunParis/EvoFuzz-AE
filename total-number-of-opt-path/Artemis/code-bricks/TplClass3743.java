public class TplClass3743 {

    private static final void method(int a, int b) throws Throwable {
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


public class TplClass1948 {

    private static final void method(int i, char[] b, int INC) throws Throwable {
        if (i == b.length) {
            char[] new_b = new char[i + INC];
            for (int c = 0; c < i; c++) new_b[c] = b[c];
            b = new_b;
        }
    }
}


public class TplClass1956 {

    private static final void method(int i, int wLen, char[] b, char[] w, int INC) throws Throwable {
        if (i + wLen >= b.length) {
            char[] new_b = new char[i + wLen + INC];
            for (int c = 0; c < i; c++) new_b[c] = b[c];
            b = new_b;
        }
        for (int c = 0; c < wLen; c++) b[i++] = w[c];
    }
}


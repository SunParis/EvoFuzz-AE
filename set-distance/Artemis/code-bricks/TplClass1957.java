public class TplClass1957 {

    private static final void method(int i, int wLen, char[] b, int INC) throws Throwable {
        char[] new_b = new char[i + wLen + INC];
        for (int c = 0; c < i; c++) new_b[c] = b[c];
        b = new_b;
    }
}


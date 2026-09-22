public class TplClass1943 {

    private static final void method(byte[] dummy, int result, int i, int offset) throws Throwable {
        if (offset > 0) {
            for (int j = 0; j < offset; j++) {
                result++;
                dummy[i] = 0;
            }
        }
    }
}


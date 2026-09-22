public class TplClass5924 {

    private static final void method(java.lang.Integer len, java.lang.Integer[] array, int pos) throws Throwable {
        for (int i = 0; i < len; i++) {
            array[pos++] = Integer.valueOf(i == 0 ? 0 : 1);
        }
    }
}


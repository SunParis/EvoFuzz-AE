import java.util.ArrayDeque;

public class TplClass5926 {

    private static final void method(java.lang.Integer[] array, int pos, java.util.ArrayDeque<java.lang.Integer> chunks, int off) throws Throwable {
        for (Integer len : chunks) {
            for (int i = 0; i < len; i++) {
                array[pos++] = Integer.valueOf(i == 0 ? 0 : 1);
            }
            off++;
        }
    }
}


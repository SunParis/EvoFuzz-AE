import java.util.Arrays;

public class TplClass439 {

    private static final void method(char[] a, int i) throws Throwable {
        int start = i & 7;
        int end = start + ((i >> 4) & 7);
        char value = (char) i;
        Arrays.fill(a, start, end, value);
        boolean error = false;
        for (int j = start; j < end; j++) {
            if (a[j] != value) {
                error = true;
            }
        }
        if (error)
            ;
    }
}


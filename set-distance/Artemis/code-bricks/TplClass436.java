import java.util.Arrays;

public class TplClass436 {

    private static final void method(float[] a, int i) throws Throwable {
        int start = i & 7;
        int end = start + ((i >> 4) & 7);
        float value = (float) i;
        if ((i & 1) == 1)
            value = -value;
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


import java.util.concurrent.atomic.AtomicInteger;
import java.text.NumberFormat;

public class TplClass5736 {

    private static final void method(java.text.NumberFormat nf, int[] ints) throws Throwable {
        for (int j = 0; j < ints.length; j++) {
            String s_i = nf.format(new Integer(ints[j]));
            String s_ai = nf.format(new AtomicInteger(ints[j]));
            if (!s_i.equals(s_ai)) {
            }
        }
    }
}


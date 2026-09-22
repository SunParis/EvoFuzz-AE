import java.util.concurrent.atomic.AtomicInteger;
import java.text.NumberFormat;

public class TplClass5741 {

    private static final void method(java.text.NumberFormat nf, int j, int[] ints) throws Throwable {
        String s_i = nf.format(new Integer(ints[j]));
        String s_ai = nf.format(new AtomicInteger(ints[j]));
        if (!s_i.equals(s_ai)) {
        }
    }
}


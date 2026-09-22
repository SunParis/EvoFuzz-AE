import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.NumberFormat;

public class TplClass5740 {

    private static final void method(long[] longs, int[] ints) throws Throwable {
        NumberFormat nf = NumberFormat.getInstance();
        for (int j = 0; j < ints.length; j++) {
            String s_i = nf.format(new Integer(ints[j]));
            String s_ai = nf.format(new AtomicInteger(ints[j]));
            if (!s_i.equals(s_ai)) {
            }
        }
        for (int j = 0; j < longs.length; j++) {
            String s_l = nf.format(new Long(longs[j]));
            String s_al = nf.format(new AtomicLong(longs[j]));
            if (!s_l.equals(s_al)) {
            }
        }
    }
}


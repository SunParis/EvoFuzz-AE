import java.util.concurrent.atomic.AtomicLong;
import java.text.NumberFormat;

public class TplClass5742 {

    private static final void method(java.text.NumberFormat nf, long[] longs, int j) throws Throwable {
        String s_l = nf.format(new Long(longs[j]));
        String s_al = nf.format(new AtomicLong(longs[j]));
        if (!s_l.equals(s_al)) {
        }
    }
}


import java.util.BitSet;
import java.util.List;

public class TplClass7809 {

    private static final void method(java.util.BitSet s, java.util.List<java.lang.Integer> up) throws Throwable {
        for (int i = s.nextSetBit(0); i >= 0; i = s.nextSetBit(i + 1)) up.add(i);
    }
}


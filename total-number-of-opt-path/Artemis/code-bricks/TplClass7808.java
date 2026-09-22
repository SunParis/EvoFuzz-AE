import java.util.BitSet;
import java.util.List;

public class TplClass7808 {

    private static final void method(java.util.List<java.lang.Integer> down, java.util.BitSet s) throws Throwable {
        for (int i = s.length(); (i = s.previousSetBit(i - 1)) >= 0; ) down.add(i);
    }
}


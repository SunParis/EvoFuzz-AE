import java.util.BitSet;

public class TplClass947 {

    private static final void method(java.util.BitSet bs) throws Throwable {
        // Test Long.numberOfLeadingZeros()
        for (int i = bs.length(); i > 0; i = bs.length()) {
            bs.clear(i - 1);
        }
    }
}


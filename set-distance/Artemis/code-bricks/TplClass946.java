import java.util.BitSet;

public class TplClass946 {

    private static final void method(java.util.BitSet bs, int check_bits) throws Throwable {
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
            check_bits++;
        }
    }
}


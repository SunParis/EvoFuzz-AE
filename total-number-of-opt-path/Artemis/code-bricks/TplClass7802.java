import java.util.BitSet;

public class TplClass7802 {

    private static final void method() throws Throwable {
        // create 2 test bitsets
        BitSet setOne = new BitSet();
        BitSet setTwo = new BitSet();
        setOne.set(64);
        setTwo.set(129);
        // exponential set growth causing memory depletion
        for (int i = 0; i < 50; i++) {
            setOne.or(setTwo);
            setTwo.or(setOne);
        }
    }
}


import java.util.BitSet;

public class TplClass7801 {

    private static final void method(java.util.BitSet setTwo, java.util.BitSet setOne) throws Throwable {
        // exponential set growth causing memory depletion
        for (int i = 0; i < 50; i++) {
            setOne.or(setTwo);
            setTwo.or(setOne);
        }
    }
}


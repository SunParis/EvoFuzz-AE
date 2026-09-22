public class TplClass2274 {

    private static final void method(int result, boolean check1, int internal, boolean check2, int mZenMode) throws Throwable {
        if (check1) {
            // This block is to ensure `result` is a phi in the return block. Without this block
            // the compiler could just generate one block with selects.
            if (check2) {
                mZenMode = 42;
            }
            result = (internal == 1) ? 1 : 0;
        }
    }
}


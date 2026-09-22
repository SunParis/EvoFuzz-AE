public class TplClass2277 {

    private static final void method(int result, int internal, boolean check2, int mZenMode) throws Throwable {
        // the compiler could just generate one block with selects.
        if (check2) {
            mZenMode = 42;
        }
        result = (internal == 1) ? 1 : 0;
    }
}


public class TplClass2275 {

    private static final void method(int mZenMode, boolean check2) throws Throwable {
        // the compiler could just generate one block with selects.
        if (check2) {
            mZenMode = 42;
        }
    }
}


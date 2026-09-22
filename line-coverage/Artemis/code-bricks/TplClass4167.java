public class TplClass4167 {

    private static final void method(int result, int i, int staticField) throws Throwable {
        for (int j = 0; j < 10; ++j) {
            // The operation has been hoisted out of the inner loop.
            // Note that we depend on the compiler's block numbering to
            // check if it has been moved.
            result += staticField * i;
        }
    }
}


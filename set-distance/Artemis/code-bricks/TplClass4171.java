public class TplClass4171 {

    private static final void method(int result, int x, int y) throws Throwable {
        for (int i = 0; i < 10; i++) {
            // The intrinsic call to max used inside the loop is invariant.
            // As a result, the call itself can be moved out of the loop body.
            result += Math.max(x, y);
        }
    }
}


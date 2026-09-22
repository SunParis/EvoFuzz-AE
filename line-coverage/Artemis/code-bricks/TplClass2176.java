public class TplClass2176 {

    private static final void method(float a, float b, boolean test2) throws Throwable {
        // Use a different condition to avoid having dx being too clever.
        if (test2) {
            // Type propagation now realizes that `b` must be of type float. So
            // it requests a float equivalent for `b`. Because the phi for `a` is
            // next to the phi for `b` in the phi list, the compiler used to crash,
            // assuming that a float phi following a phi *must* be for the same DEX
            // register.
            a = b;
        }
    }
}


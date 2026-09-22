public class TplClass4646 {

    private static final void method(int x, int y, int z) throws Throwable {
        z = x - y;
        // Prevent HSelect simplification by having a branch with multiple instructions.
        System.nanoTime();
    }
}


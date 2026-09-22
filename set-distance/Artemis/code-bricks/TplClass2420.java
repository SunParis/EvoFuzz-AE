public class TplClass2420 {

    private static final void method(int x) throws Throwable {
        // Simple if-else requires inspecting bounds of resulting selects.
        if (x > 100) {
            x = 100;
        } else if (x < -100) {
            x = -100;
        }
    }
}


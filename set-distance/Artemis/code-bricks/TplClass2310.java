public class TplClass2310 {

    private static final void method(int closed) throws Throwable {
        for (int i = 0; i < 10; i++) {
            // Trivial if becomes trivial select at HIR level.
            // Make sure this is still recognized as induction.
            if (i < 5) {
                closed += 7;
            } else {
                closed += 7;
            }
        }
    }
}


public class TplClass2952 {

    private static final void method(int sA) throws Throwable {
        // Unrolling each loop in turn ultimately removes the complete nest!
        for (int i = 4; i < 5; i++) {
            for (int j = 5; j < 6; j++) {
                for (int k = 6; k < 7; k++) {
                    sA = k;
                }
            }
        }
    }
}


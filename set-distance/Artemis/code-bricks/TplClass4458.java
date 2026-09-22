public class TplClass4458 {

    private static final void method(int acc, int[][] x, int i, int j, float f) throws Throwable {
        // The full body of a do-while loop is the loop header.
        do {
            // Some "noise" to avoid hoisting the array reference
            // before the dynamic BCE phase runs.
            f++;
            // The invariant array reference with corresponding bounds check
            // is a candidate for hoisting when dynamic BCE runs. If it is
            // not moved to the proper loop preheader, the wrong values
            // cause the test to fail.
            acc += x[i][i];
        } while (++j < i);
    }
}


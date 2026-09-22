public class TplClass2562 {

    private static final void method(long NUM_ITERATIONS, double negInfinity) throws Throwable {
        long sumInf = 0;
        long sumRes = 0;
        for (long i = 0; i < NUM_ITERATIONS; i++) {
            // Every second iteration, sumInf becomes 0
            sumInf += (long) negInfinity;
            // Some extra work for compilers to make this
            // loop seem important
            if (sumInf == Long.MIN_VALUE) {
                sumRes++;
            }
        }
        if (sumRes == NUM_ITERATIONS / 2) {
        } else {
        }
    }
}


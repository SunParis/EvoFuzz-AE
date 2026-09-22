public class TplClass2559 {

    private static final void method(double negInfinity, long NUM_ITERATIONS, long sumRes, long sumInf) throws Throwable {
        for (long i = 0; i < NUM_ITERATIONS; i++) {
            // Every second iteration, sumInf becomes 0
            sumInf += (long) negInfinity;
            // Some extra work for compilers to make this
            // loop seem important
            if (sumInf == Long.MIN_VALUE) {
                sumRes++;
            }
        }
    }
}


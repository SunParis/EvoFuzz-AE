public class TplClass2563 {

    private static final void method(double negInfinity, long sumRes, long sumInf) throws Throwable {
        // Every second iteration, sumInf becomes 0
        sumInf += (long) negInfinity;
        // loop seem important
        if (sumInf == Long.MIN_VALUE) {
            sumRes++;
        }
    }
}


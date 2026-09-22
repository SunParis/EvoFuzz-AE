public class TplClass1035 {

    private static final void method(long l1v, double d1v, long l2v, double d2v) throws Throwable {
        long l1 = l1v;
        double d1 = d1v;
        long l2 = l2v;
        double d2 = d2v;
        // Run long enough to induce an OSR
        for (int i = 0; i < 10000000; i++) {
        }
        boolean error = false;
        if (l1 != l1v) {
            error = true;
        }
        if (l2 != l2v) {
            error = true;
        }
        if (d1 != d1v) {
            error = true;
        }
        if (d2 != d2v) {
            error = true;
        }
        if (error) {
        }
    }
}


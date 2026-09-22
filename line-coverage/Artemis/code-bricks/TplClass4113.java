public class TplClass4113 {

    private static final void method(int NUM_TRIES, long min, long max, long elapsed_to_report, boolean showTime, int num_failures) throws Throwable {
        showTime = true;
        if (elapsed_to_report < min) {
            // This can legitimately happen due to premature wake-ups.
            // This seems rare and unexpected enough in practice that we should
        } else if (elapsed_to_report > max) {
        }
    }
}


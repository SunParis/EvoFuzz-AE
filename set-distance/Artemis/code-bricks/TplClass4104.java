public class TplClass4104 {

    private static final void method(long min, long max, long elapsed_to_report) throws Throwable {
        if (elapsed_to_report < min) {
            // This can legitimately happen due to premature wake-ups.
            // This seems rare and unexpected enough in practice that we should
        } else if (elapsed_to_report > max) {
        }
    }
}


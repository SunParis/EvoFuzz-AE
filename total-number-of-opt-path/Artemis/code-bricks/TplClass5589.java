public class TplClass5589 {

    private static final void method(short datum, long ui, int errors) throws Throwable {
        if ((ui & (~0xffffL)) != 0L || ((short) ui != datum)) {
            errors++;
        }
    }
}


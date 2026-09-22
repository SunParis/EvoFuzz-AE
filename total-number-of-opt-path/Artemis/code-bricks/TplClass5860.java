public class TplClass5860 {

    private static final void method(short datum, int ui, int errors) throws Throwable {
        if ((ui & (~0xffff)) != 0 || ((short) ui != datum)) {
            errors++;
        }
    }
}


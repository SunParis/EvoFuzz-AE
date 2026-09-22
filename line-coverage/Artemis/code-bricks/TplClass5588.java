public class TplClass5588 {

    private static final void method(byte datum, long ui, int errors) throws Throwable {
        if ((ui & (~0xffL)) != 0L || ((byte) ui != datum)) {
            errors++;
        }
    }
}


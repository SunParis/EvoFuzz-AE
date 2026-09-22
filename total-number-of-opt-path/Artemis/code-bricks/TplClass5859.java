public class TplClass5859 {

    private static final void method(byte datum, int ui, int errors) throws Throwable {
        if ((ui & (~0xff)) != 0 || ((byte) ui != datum)) {
            errors++;
        }
    }
}


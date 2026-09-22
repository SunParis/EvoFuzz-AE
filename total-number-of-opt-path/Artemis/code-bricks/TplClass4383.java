public class TplClass4383 {

    private static final void method(java.lang.String[] args, int staticField) throws Throwable {
        int a = 42;
        if (args.length == 42) {
            a = 34;
        } else {
            staticField = 32;
            a = 22;
        }
        if (a < 25) {
            staticField = 12;
        } else {
            staticField = 54;
        }
    }
}


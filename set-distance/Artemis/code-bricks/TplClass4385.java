public class TplClass4385 {

    private static final void method(java.lang.String[] args, int otherStaticField, int staticField) throws Throwable {
        int a = 42;
        int b = otherStaticField;
        if (args.length == 42) {
            a = 34;
        } else {
            staticField = 32;
            a = b;
        }
        if (a == b) {
            staticField = 12;
        } else {
            staticField = 54;
        }
    }
}


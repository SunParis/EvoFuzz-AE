public class TplClass4380 {

    private static final void method(java.lang.String[] args, int otherStaticField, int staticField) throws Throwable {
        int a = 42;
        if (args.length == 42) {
            a = 34;
        } else {
            staticField = 32;
            a = otherStaticField;
        }
        if (a == 42) {
            staticField = 12;
        } else {
            staticField = 54;
        }
    }
}


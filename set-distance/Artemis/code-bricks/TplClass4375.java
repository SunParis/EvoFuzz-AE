public class TplClass4375 {

    private static final void method(java.lang.String[] args, int staticField) throws Throwable {
        boolean myVar = false;
        if (args.length == 42) {
            myVar = true;
        } else {
            staticField = 32;
            myVar = false;
        }
        if (myVar) {
            staticField = 12;
        } else {
            staticField = 54;
        }
    }
}


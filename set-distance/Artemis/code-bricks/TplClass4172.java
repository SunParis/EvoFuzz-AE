public class TplClass4172 {

    private static final void method(int result, int a, int b, int staticField) throws Throwable {
        while (b < 5) {
            // a might be null, so we can't hoist the operation.
            result += staticField / a;
            b++;
        }
    }
}


public class TplClass4176 {

    private static final void method(int result, int a, int b, int staticField) throws Throwable {
        // a might be null, so we can't hoist the operation.
        result += staticField / a;
        b++;
    }
}


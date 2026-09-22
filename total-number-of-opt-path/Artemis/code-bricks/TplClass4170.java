public class TplClass4170 {

    private static final void method(int result, int x) throws Throwable {
        // As a result, the call itself can be moved out of the loop header.
        for (int i = 0; i < Math.abs(x); i++) {
            result += i;
        }
    }
}


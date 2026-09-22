public class TplClass4409 {

    private static final void method(int res, int tmp, int arg, int static_variable) throws Throwable {
        for (int i = 1; i < arg; i++) {
            tmp -= i;
            // div-zero check barrier.
            res = res / i;
            static_variable++;
        }
    }
}


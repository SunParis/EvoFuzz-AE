public class TplClass4221 {

    private static final void method(long result, long c, long tmp) throws Throwable {
        for (long i = 0; i < 100; i++) {
            tmp = i * c;
            result += i * i;
            result = i - tmp;
        }
    }
}


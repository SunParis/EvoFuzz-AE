public class TplClass4220 {

    private static final void method(int result, int c, int tmp) throws Throwable {
        for (int i = 0; i < 100; i++) {
            tmp = i * c;
            result += i * i;
            result = i - tmp;
        }
    }
}


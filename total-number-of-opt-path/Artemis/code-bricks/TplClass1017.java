public class TplClass1017 {

    private static final void method(int[] src, int i) throws Throwable {
        int tmp = src[i];
        src[i] = src[i - 1];
        src[i - 1] = tmp;
    }
}


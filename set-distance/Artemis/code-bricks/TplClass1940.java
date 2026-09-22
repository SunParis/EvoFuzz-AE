public class TplClass1940 {

    private static final void method(int NCOPY, int[] dst2, int[] src2, int OVERFLOW) throws Throwable {
        int N;
        int SIZE;
        N = Integer.MAX_VALUE / 4 + OVERFLOW;
        System.arraycopy(src2, 0, dst2, N, NCOPY);
        System.arraycopy(dst2, N, src2, 0, NCOPY);
    }
}


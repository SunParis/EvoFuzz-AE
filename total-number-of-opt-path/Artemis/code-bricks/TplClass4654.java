public class TplClass4654 {

    private static final void method(long y, long j, long k, long l) throws Throwable {
        for (long i = -64; i < 64; i++) {
            y = i & i - 1;
            j += y;
            j = j & j - 1;
            k += j;
            k = k & k - 1;
            l += k;
            l = l & l - 1;
        }
    }
}


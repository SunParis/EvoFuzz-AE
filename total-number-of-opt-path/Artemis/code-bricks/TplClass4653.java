public class TplClass4653 {

    private static final void method(int y, int j, int k, int l) throws Throwable {
        for (int i = -64; i < 64; i++) {
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


public class TplClass3045 {

    private static final void method(int i, short[] s1, int sad, short[] s2) throws Throwable {
        short s = s1[i];
        short p = s2[i];
        sad += s >= p ? s - p : p - s;
    }
}


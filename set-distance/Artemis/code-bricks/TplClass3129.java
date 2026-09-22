public class TplClass3129 {

    private static final void method(int i, char[] s1, int sad, char[] s2) throws Throwable {
        short s = (short) s1[i];
        short p = (short) s2[i];
        sad += s >= p ? s - p : p - s;
    }
}


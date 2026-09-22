public class TplClass3126 {

    private static final void method(int i, char[] s1, short sad, char[] s2) throws Throwable {
        short s = (short) s1[i];
        short p = (short) s2[i];
        sad += s >= p ? s - p : p - s;
    }
}


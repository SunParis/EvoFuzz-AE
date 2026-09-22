public class TplClass3127 {

    private static final void method(int i, char[] s1, short sad, char[] s2) throws Throwable {
        short s = (short) s1[i];
        short p = (short) s2[i];
        int x = s - p;
        if (x < 0)
            x = -x;
        sad += x;
    }
}


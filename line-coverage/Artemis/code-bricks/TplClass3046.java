public class TplClass3046 {

    private static final void method(int i, short[] s1, int sad, short[] s2) throws Throwable {
        short s = s1[i];
        short p = s2[i];
        int x = s - p;
        if (x < 0)
            x = -x;
        sad += x;
    }
}


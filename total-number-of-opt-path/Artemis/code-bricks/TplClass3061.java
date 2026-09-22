public class TplClass3061 {

    private static final void method(int i, char[] s1, char sad, char[] s2) throws Throwable {
        char s = s1[i];
        char p = s2[i];
        int x = s - p;
        if (x < 0)
            x = -x;
        sad += x;
    }
}


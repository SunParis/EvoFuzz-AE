public class TplClass3060 {

    private static final void method(int i, char[] s1, char sad, char[] s2) throws Throwable {
        char s = s1[i];
        char p = s2[i];
        sad += s >= p ? s - p : p - s;
    }
}


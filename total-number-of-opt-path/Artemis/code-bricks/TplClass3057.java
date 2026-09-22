public class TplClass3057 {

    private static final void method(int k, char[] s1, int n, char[] interesting, char[] s2) throws Throwable {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s1[k] = interesting[i];
                s2[k] = interesting[j];
                k++;
            }
        }
    }
}


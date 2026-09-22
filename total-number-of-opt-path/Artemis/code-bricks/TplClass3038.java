public class TplClass3038 {

    private static final void method(int k, short[] s1, int n, short[] interesting, short[] s2) throws Throwable {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s1[k] = interesting[i];
                s2[k] = interesting[j];
                k++;
            }
        }
    }
}


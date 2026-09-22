import java.io.InputStreamReader;

public class TplClass5103 {

    private static final void method(char[] cs, java.io.InputStreamReader r, java.lang.String enc, int n) throws Throwable {
        for (int i = 0; i < n; ) {
            int m;
            if ((m = r.read(cs, i, n - i)) < 0)
                ;
            i += m;
        }
    }
}


import java.io.InputStreamReader;

public class TplClass5122 {

    private static final void method(char[] cs, int i, java.io.InputStreamReader r, java.lang.String enc, int n) throws Throwable {
        int m;
        if ((m = r.read(cs, i, n - i)) < 0)
            ;
        i += m;
    }
}


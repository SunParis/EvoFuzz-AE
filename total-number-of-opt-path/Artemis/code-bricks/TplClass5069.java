import java.util.regex.Pattern;
import java.util.Arrays;

public class TplClass5069 {

    private static final void method(java.util.regex.Pattern p, int limit, java.lang.String regex, java.lang.String src) throws Throwable {
        if (!Arrays.equals(src.split(regex, limit), p.split(src, limit)))
            ;
    }
}


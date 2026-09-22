import java.util.regex.Pattern;
import java.util.Arrays;

public class TplClass5056 {

    private static final void method(java.util.regex.Pattern p, java.lang.String[] srcStrs, java.lang.String regex) throws Throwable {
        for (String src : srcStrs) {
            for (int limit = -2; limit < 3; limit++) {
                if (!Arrays.equals(src.split(regex, limit), p.split(src, limit)))
                    ;
            }
        }
    }
}


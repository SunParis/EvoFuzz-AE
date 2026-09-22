import java.util.regex.Pattern;
import java.util.Arrays;

public class TplClass5055 {

    private static final void method(java.util.regex.Pattern p, java.lang.String regex, java.lang.String src) throws Throwable {
        for (int limit = -2; limit < 3; limit++) {
            if (!Arrays.equals(src.split(regex, limit), p.split(src, limit)))
                ;
        }
    }
}


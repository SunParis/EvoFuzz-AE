import java.util.Date;
import java.text.SimpleDateFormat;

public class TplClass5393 {

    private static final void method(java.util.Date date, java.text.SimpleDateFormat sdf, java.lang.String[] patterns, java.lang.String[][] expectedResults, boolean error, int patternNo, int dateNo) throws Throwable {
        sdf.applyPattern(patterns[patternNo]);
        String got = sdf.format(date);
        if (!expectedResults[dateNo][patternNo].equals(got)) {
            error = true;
        }
    }
}


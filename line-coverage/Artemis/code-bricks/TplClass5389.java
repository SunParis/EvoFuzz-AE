import java.util.Date;
import java.text.SimpleDateFormat;

public class TplClass5389 {

    private static final void method(java.util.Date date, java.text.SimpleDateFormat sdf, java.lang.String[] patterns, java.lang.String[][] expectedResults, boolean error, int dateNo) throws Throwable {
        for (int patternNo = 0; patternNo < patterns.length; patternNo++) {
            sdf.applyPattern(patterns[patternNo]);
            String got = sdf.format(date);
            if (!expectedResults[dateNo][patternNo].equals(got)) {
                error = true;
            }
        }
    }
}


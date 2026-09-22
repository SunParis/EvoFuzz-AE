import java.util.Date;
import java.text.SimpleDateFormat;

public class TplClass5392 {

    private static final void method(java.text.SimpleDateFormat sdf, java.lang.String[] patterns, java.util.Date[] dates, java.lang.String[][] expectedResults, boolean error, int dateNo) throws Throwable {
        Date date = dates[dateNo];
        for (int patternNo = 0; patternNo < patterns.length; patternNo++) {
            sdf.applyPattern(patterns[patternNo]);
            String got = sdf.format(date);
            if (!expectedResults[dateNo][patternNo].equals(got)) {
                error = true;
            }
        }
    }
}


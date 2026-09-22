import java.util.Date;
import java.text.SimpleDateFormat;

public class TplClass5388 {

    private static final void method(java.util.Date[] dates, java.text.SimpleDateFormat sdf, java.lang.String[][] expectedResults, boolean error, java.lang.String[] patterns) throws Throwable {
        for (int dateNo = 0; dateNo < dates.length; dateNo++) {
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
}


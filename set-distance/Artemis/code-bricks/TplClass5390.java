import java.util.Date;

public class TplClass5390 {

    private static final void method(java.util.Date date, java.lang.String[] patterns, java.lang.String[][] expectedResults, boolean error, int patternNo, java.lang.String got, int dateNo) throws Throwable {
        if (!expectedResults[dateNo][patternNo].equals(got)) {
            error = true;
        }
    }
}


import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

public class TplClass5385 {

    private static final void method() throws Throwable {
        DateFormatSymbols symbols = new DateFormatSymbols();
        SimpleDateFormat df = new SimpleDateFormat("E hh:mm", symbols);
        // change value of field
        symbols.setLocalPatternChars("abcdefghijklmonpqr");
    }
}


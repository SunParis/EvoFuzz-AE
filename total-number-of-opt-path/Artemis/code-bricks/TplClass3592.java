import java.util.Arrays;
import java.util.Locale;
import java.text.DateFormatSymbols;

public class TplClass3592 {

    private static final void method() throws Throwable {
        Locale usa = new Locale("en", "US");
        DateFormatSymbols syms = DateFormatSymbols.getInstance(usa);
        String[] list = syms.getAmPmStrings();
    }
}


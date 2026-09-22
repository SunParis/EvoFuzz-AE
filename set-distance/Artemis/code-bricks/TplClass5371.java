import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.DateFormatSymbols;
import java.text.DateFormat;

public class TplClass5371 {

    private static final void method() throws Throwable {
        try {
            new SimpleDateFormat("yy", (Locale) null);
        } catch (NullPointerException e) {
        }
        try {
            new SimpleDateFormat((String) null, Locale.getDefault());
        } catch (NullPointerException e) {
        }
        try {
            new SimpleDateFormat("yy", (DateFormatSymbols) null);
        } catch (NullPointerException e) {
        }
        try {
            new SimpleDateFormat((String) null, DateFormatSymbols.getInstance());
        } catch (NullPointerException e) {
        }
        try {
            DateFormat.getTimeInstance(DateFormat.FULL, null);
        } catch (NullPointerException e) {
        }
        try {
            DateFormat.getDateInstance(DateFormat.FULL, null);
        } catch (NullPointerException e) {
        }
        try {
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, null);
        } catch (NullPointerException e) {
        }
    }
}


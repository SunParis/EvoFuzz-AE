import java.util.Locale;

public class TplClass7457 {

    private static final void method(java.util.Locale[] systemLocales, int j, java.util.Locale lowest) throws Throwable {
        if (lowest == null || systemLocales[j].toString().compareTo(lowest.toString()) < 0)
            lowest = systemLocales[j];
    }
}


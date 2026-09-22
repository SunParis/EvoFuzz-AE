import java.util.Locale;

public class TplClass7444 {

    private static final void method() throws Throwable {
        String language = "en";
        String country = "US";
        String variant = "socal";
        Locale aLocale = new Locale(language, country, variant);
        String localeVariant = aLocale.getVariant();
        if (localeVariant.equals(variant)) {
        } else {
        }
    }
}


import java.util.List;
import java.net.HttpCookie;
import java.util.Locale;

public class TplClass4968 {

    private static final void method(java.util.Locale reservedLocale) throws Throwable {
        try {
            // Forces a non US locale
            Locale.setDefault(Locale.FRANCE);
            List<HttpCookie> cookies = HttpCookie.parse("set-cookie:" + " CUSTOMER=WILE_E_COYOTE;" + " expires=Sat, 09-Nov-2041 23:12:40 GMT");
            if (cookies == null || cookies.isEmpty()) {
            }
            for (HttpCookie c : cookies) {
                if (c.getMaxAge() == 0) {
                }
            }
        } finally {
            // restore the reserved locale
            Locale.setDefault(reservedLocale);
        }
    }
}


import java.util.List;
import java.net.HttpCookie;

public class TplClass4978 {

    private static final void method(java.util.List<java.net.HttpCookie> addedCookieList, boolean fail, java.net.HttpCookie cookie) throws Throwable {
        for (HttpCookie chip : addedCookieList) {
            if (!chip.equals(cookie)) {
                fail = true;
            }
        }
    }
}


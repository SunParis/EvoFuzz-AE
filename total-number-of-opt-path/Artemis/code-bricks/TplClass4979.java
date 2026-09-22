import java.net.CookieStore;

public class TplClass4979 {

    private static final void method(boolean fail, java.net.CookieStore cookieStore) throws Throwable {
        // Check if removeAll() retrurns false on an empty CookieStore
        if (cookieStore.removeAll()) {
            fail = true;
        }
    }
}


import java.util.Map;
import java.util.List;

public class TplClass4977 {

    private static final void method(java.util.Map<java.lang.String, java.lang.String> cookies, java.util.Map<java.lang.String, java.util.List<java.lang.String>> responseHeaders) throws Throwable {
        // check response has cookies[1]
        List<String> l = responseHeaders.get("Set-Cookie2");
        String value = l.get(0);
        if (!value.equals(cookies.get("Set-Cookie2"))) {
        }
    }
}


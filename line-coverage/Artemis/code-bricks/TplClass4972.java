import java.util.Map;

public class TplClass4972 {

    private static final void method(java.lang.String x, java.lang.String header, boolean flag, java.util.Map<java.lang.String, java.lang.String> cookies) throws Throwable {
        if (x.startsWith(header)) {
            if (x.equals("Cookie: " + ((String) cookies.get("Cookie")))) {
                flag = true;
            }
        }
    }
}


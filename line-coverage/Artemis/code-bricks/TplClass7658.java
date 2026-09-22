import java.net.URLEncoder;
import java.net.URLDecoder;

public class TplClass7658 {

    private static final void method() throws Throwable {
        String str = "fds@$";
        String encStr = URLEncoder.encode(str);
        String decStr = URLDecoder.decode(encStr);
    }
}


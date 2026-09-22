import java.net.InetAddress;

public class TplClass6432 {

    private static final void method(java.lang.String msg, java.net.InetAddress actual, int failures) throws Throwable {
        if (actual.isAnyLocalAddress()) {
        } else {
            failures++;
        }
    }
}


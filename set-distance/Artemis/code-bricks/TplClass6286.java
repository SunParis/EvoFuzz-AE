import java.security.AccessController;
import java.security.BasicPermission;

public class TplClass6286 {

    private static final void method() throws Throwable {
        AccessController.checkPermission(new BasicPermission("no such permission") {
        });
    }
}


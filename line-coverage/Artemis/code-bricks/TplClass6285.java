import java.security.AccessControlException;
import java.security.AccessController;
import java.security.BasicPermission;

public class TplClass6285 {

    private static final void method() throws Throwable {
        try {
            AccessController.checkPermission(new BasicPermission("no such permission") {
            });
        } catch (NullPointerException npe) {
        } catch (AccessControlException ace) {
        }
    }
}


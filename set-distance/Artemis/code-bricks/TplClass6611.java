import java.net.SocketPermission;

public class TplClass6611 {

    private static final void method() throws Throwable {
        SocketPermission star_All = new SocketPermission("*.blabla.bla", "listen,accept,connect");
        SocketPermission www_All = new SocketPermission("bla.blabla.bla", "listen,accept,connect");
        if (!star_All.implies(www_All)) {
        }
    }
}


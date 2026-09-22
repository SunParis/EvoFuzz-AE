import java.net.SocketPermission;

public class TplClass6614 {

    private static final void method() throws Throwable {
        SocketPermission p = new SocketPermission("invlidhost", "connect");
        if (!p.implies(p))
            ;
        SocketPermission p1 = new SocketPermission("invlidhost", "connect");
        if (!p.implies(p1))
            ;
    }
}


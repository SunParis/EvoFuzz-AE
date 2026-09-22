import java.net.Socket;

public class TplClass6081 {

    private static final void method(java.net.Socket s) throws Throwable {
        try {
            /* gives time for 'write' to block */
            Thread.sleep(5000);
            s.close();
        } catch (Exception e) {
        }
    }
}


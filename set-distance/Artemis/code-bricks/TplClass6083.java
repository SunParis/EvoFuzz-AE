import java.net.Socket;

public class TplClass6083 {

    private static final void method(java.net.Socket s) throws Throwable {
        /* gives time for 'write' to block */
        Thread.sleep(5000);
        s.close();
    }
}


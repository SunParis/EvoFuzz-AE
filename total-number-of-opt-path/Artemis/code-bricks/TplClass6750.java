import java.net.Socket;

public class TplClass6750 {

    private static final void method(int timeout, java.net.Socket s) throws Throwable {
        Thread.currentThread().sleep(timeout);
        s.close();
    }
}


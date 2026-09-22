import java.net.Socket;

public class TplClass6748 {

    private static final void method(int timeout, java.net.Socket s) throws Throwable {
        try {
            Thread.currentThread().sleep(timeout);
            s.close();
        } catch (Exception e) {
        }
    }
}


import java.net.UnknownHostException;
import java.net.Socket;

public class TplClass6768 {

    private static final void method() throws Throwable {
        try {
            Socket s = new Socket("ho st", 8000);
        } catch (UnknownHostException e) {
            // Expected
        } catch (Exception e) {
        }
    }
}


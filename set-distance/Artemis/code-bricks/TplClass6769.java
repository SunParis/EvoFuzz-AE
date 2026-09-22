import java.net.UnknownHostException;
import java.net.Socket;

public class TplClass6769 {

    private static final void method() throws Throwable {
        try {
            Socket s = new Socket("\u67f4\u7530\u82b3\u6a39", 8000);
        } catch (UnknownHostException e) {
            // Expected
        } catch (Exception e) {
        }
        try {
            Socket s = new Socket("ho st", 8000);
        } catch (UnknownHostException e) {
            // Expected
        } catch (Exception e) {
        }
    }
}


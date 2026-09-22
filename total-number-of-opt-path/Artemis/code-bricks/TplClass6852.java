import java.net.DatagramSocket;

public class TplClass6852 {

    private static final void method(int IPTOS_RELIABILITY, int failures, java.net.DatagramSocket s) throws Throwable {
        try {
            s.setTrafficClass(IPTOS_RELIABILITY);
            int tc = s.getTrafficClass();
        } catch (Exception e) {
            failures++;
        }
    }
}


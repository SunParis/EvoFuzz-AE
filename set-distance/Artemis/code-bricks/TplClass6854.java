import java.net.DatagramSocket;

public class TplClass6854 {

    private static final void method(int IPTOS_RELIABILITY, java.net.DatagramSocket s) throws Throwable {
        s.setTrafficClass(IPTOS_RELIABILITY);
        int tc = s.getTrafficClass();
    }
}


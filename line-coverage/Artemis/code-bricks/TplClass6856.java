import java.net.Socket;

public class TplClass6856 {

    private static final void method(int IPTOS_RELIABILITY, java.net.Socket s) throws Throwable {
        s.setTrafficClass(IPTOS_RELIABILITY);
        int tc = s.getTrafficClass();
    }
}


import java.net.ServerSocket;

public class TplClass7257 {

    private static final void method(java.net.ServerSocket ss, int REPS) throws Throwable {
        for (int i = 0; i < REPS; i++) {
            ss.accept().close();
        }
    }
}


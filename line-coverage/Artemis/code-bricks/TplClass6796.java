import java.net.Socket;

public class TplClass6796 {

    private static final void method(java.net.Socket s) throws Throwable {
        for (int i = 0; i < 1000000; i++) {
            // buggy JDK will run out of memory in this loop
            s.getOutputStream();
            // test InputStream also while we're here
            s.getInputStream();
            if (i % 100000 == 0)
                ;
        }
    }
}


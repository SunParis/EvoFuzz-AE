import java.net.Socket;

public class TplClass6800 {

    private static final void method(int i, java.net.Socket s) throws Throwable {
        // buggy JDK will run out of memory in this loop
        s.getOutputStream();
        // test InputStream also while we're here
        s.getInputStream();
        if (i % 100000 == 0)
            ;
    }
}


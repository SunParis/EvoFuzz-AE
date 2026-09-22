import java.net.ServerSocket;
import java.io.PrintStream;
import java.net.Socket;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TplClass5712 {

    private static final void method(java.net.ServerSocket ss) throws Throwable {
        Socket s = ss.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String req = in.readLine();
        PrintStream out = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
        /* send the header */
        out.print("HTTP/1.1 403 Forbidden\r\n");
        out.print("\r\n");
        out.flush();
        s.close();
        ss.close();
    }
}


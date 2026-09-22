import java.io.BufferedReader;
import java.net.InetAddress;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.InputStream;

public class TplClass6363 {

    private static final void method() throws Throwable {
        InetAddress sin = null;
        Socket soc = null, soc1 = null;
        InputStream is = null;
        OutputStream os = null;
        ServerSocket srv = null;
        int port = 0;
        int tout = 1000;
        sin = InetAddress.getLocalHost();
        srv = new ServerSocket(port);
        port = srv.getLocalPort();
        soc = new Socket(sin, port);
        soc1 = srv.accept();
        BufferedReader bin = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(soc1.getOutputStream()));
        bout.write("hello");
        bout.newLine();
        bout.flush();
        String reply = bin.readLine();
        if (!reply.equals("hello"))
            ;
        soc.close();
        soc1.close();
        srv.close();
    }
}


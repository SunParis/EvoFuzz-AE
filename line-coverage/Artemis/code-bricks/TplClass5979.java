import java.io.OutputStream;
import java.io.InputStream;

public class TplClass5979 {

    private static final void method(java.io.InputStream in, java.io.OutputStream out) throws Throwable {
        out.write('a');
        out.flush();
        if (// got end-of-stream
        in.read() == -1)
            ;
    }
}


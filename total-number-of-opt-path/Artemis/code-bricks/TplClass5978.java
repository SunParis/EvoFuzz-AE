import java.io.OutputStream;
import java.io.InputStream;

public class TplClass5978 {

    private static final void method(java.io.InputStream in, java.lang.Exception savedException, java.io.OutputStream out) throws Throwable {
        try {
            out.write('a');
            out.flush();
            if (// got end-of-stream
            in.read() == -1)
                ;
        } catch (Exception e) {
            savedException = e;
        }
    }
}


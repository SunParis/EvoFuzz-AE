import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

public class TplClass6504 {

    private static final void method(byte[] deflated) throws Throwable {
        OutputStream os = new BufferedOutputStream(new FileOutputStream("deflated.zip"));
        try {
            os.write(deflated);
        } finally {
            os.close();
        }
    }
}


import java.io.OutputStream;

public class TplClass6499 {

    private static final void method(java.io.OutputStream os, byte[] deflated) throws Throwable {
        try {
            os.write(deflated);
        } finally {
            os.close();
        }
    }
}


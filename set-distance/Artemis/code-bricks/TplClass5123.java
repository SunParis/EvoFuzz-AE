import java.nio.charset.Charset;

public class TplClass5123 {

    private static final void method(java.nio.charset.Charset charset, byte[] bytes) throws Throwable {
        String s = new String(bytes, charset);
        // Replace the thread-local encoder with this one.
        byte[] bb = s.getBytes(Charset.forName("UTF-16LE"));
        if (bytes.length != bb.length) {
        } else {
            boolean diff = false;
            // Expect different byte[] between UTF-16LE and UTF-16BE
            // even though encoder was previously cached by last call
            // to getBytes().
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != bb[i])
                    diff = true;
            }
            if (!diff)
                ;
        }
    }
}


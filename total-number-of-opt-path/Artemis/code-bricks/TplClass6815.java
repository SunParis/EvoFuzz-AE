import java.io.InputStream;

public class TplClass6815 {

    private static final void method(boolean isClient, java.lang.String s, java.io.InputStream clis) throws Throwable {
        if (isClient) {
            // read Hello world from client (during which oob byte must have been read)
            s = "Hello";
            for (int y = 0; y < s.length(); y++) {
                int c = clis.read();
                if (c != (int) s.charAt(y)) {
                }
            }
            if (clis.read() != 101) {
            }
            s = "World";
            for (int y = 0; y < s.length(); y++) {
                int c = clis.read();
                if (c != (int) s.charAt(y)) {
                }
            }
        }
    }
}


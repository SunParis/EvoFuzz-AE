import java.util.zip.Adler32;

public class TplClass6492 {

    private static final void method(int length, byte[] b, int offset) throws Throwable {
        (new Adler32()).update(b, offset, length);
    }
}


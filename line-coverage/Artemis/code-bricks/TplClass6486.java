import java.util.zip.Adler32;

public class TplClass6486 {

    private static final void method(int length, byte[] b, int offset) throws Throwable {
        try {
            (new Adler32()).update(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
    }
}


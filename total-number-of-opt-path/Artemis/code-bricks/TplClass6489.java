import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.Adler32;

public class TplClass6489 {

    private static final void method() throws Throwable {
        byte[] b = new byte[0];
        int offset = 4;
        int length = Integer.MAX_VALUE - 3;
        try {
            (new CRC32()).update(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
        try {
            (new Deflater()).setDictionary(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
        try {
            (new Adler32()).update(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
        try {
            (new Deflater()).deflate(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
        try {
            (new Inflater()).inflate(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
    }
}


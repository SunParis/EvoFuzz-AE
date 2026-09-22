import java.util.zip.CRC32;

public class TplClass6484 {

    private static final void method(int length, byte[] b, int offset) throws Throwable {
        try {
            (new CRC32()).update(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
    }
}


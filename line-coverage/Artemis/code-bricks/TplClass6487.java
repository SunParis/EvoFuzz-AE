import java.util.zip.Deflater;

public class TplClass6487 {

    private static final void method(int length, byte[] b, int offset) throws Throwable {
        try {
            (new Deflater()).deflate(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
    }
}


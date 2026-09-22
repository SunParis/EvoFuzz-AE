import java.util.zip.Deflater;

public class TplClass6485 {

    private static final void method(int length, byte[] b, int offset) throws Throwable {
        try {
            (new Deflater()).setDictionary(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
    }
}


import java.util.zip.Inflater;

public class TplClass6488 {

    private static final void method(int length, byte[] b, int offset) throws Throwable {
        try {
            (new Inflater()).inflate(b, offset, length);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // Correct result
        }
    }
}


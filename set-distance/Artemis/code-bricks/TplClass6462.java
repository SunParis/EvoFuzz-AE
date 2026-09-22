import java.util.zip.ZipInputStream;

public class TplClass6462 {

    private static final void method(int count, byte[] readData, java.util.zip.ZipInputStream zis, int pos) throws Throwable {
        while (count > 0) {
            count = zis.read(readData, ++pos, 1);
        }
    }
}


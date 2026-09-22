import java.io.IOException;
import java.io.RandomAccessFile;

public class TplClass4518 {

    private static final void method(java.lang.String pathName, java.io.RandomAccessFile raf, byte[] fileData) throws Throwable {
        try {
            fileData = new byte[(int) raf.length()];
            raf.readFully(fileData);
        } catch (IOException ioe) {
        } finally {
            try {
                raf.close();
            } catch (IOException ioe) {
                // drop
            }
        }
    }
}


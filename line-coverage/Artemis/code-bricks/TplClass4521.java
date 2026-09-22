import java.io.RandomAccessFile;

public class TplClass4521 {

    private static final void method(java.io.RandomAccessFile raf, byte[] fileData) throws Throwable {
        fileData = new byte[(int) raf.length()];
        raf.readFully(fileData);
    }
}


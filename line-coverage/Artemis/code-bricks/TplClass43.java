import java.util.zip.Checksum;

public class TplClass43 {

    private static final void method(int start, int length, byte[] b, java.util.zip.Checksum crc) throws Throwable {
        for (int i = 0; i < length; i++) crc.update(b[i + start]);
        crc.getValue();
    }
}


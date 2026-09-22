import java.util.zip.Checksum;

public class TplClass40 {

    private static final void method(java.util.zip.Checksum crc2, java.util.zip.Checksum crc1) throws Throwable {
        if (crc1.getValue() != crc2.getValue()) {
            String s = "value 1 = " + crc1.getValue() + ", value 2 = " + crc2.getValue();
        }
    }
}


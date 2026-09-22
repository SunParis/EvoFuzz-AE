import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class TplClass6326 {

    private static final void method(byte[] e) throws Throwable {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bs);
        try {
            ds.writeLong(System.currentTimeMillis());
        } catch (IOException ioe) {
        }
        e = bs.toByteArray();
    }
}


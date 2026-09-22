import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class TplClass6321 {

    private static final void method(byte[] e) throws Throwable {
        if (e == null) {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            DataOutputStream ds = new DataOutputStream(bs);
            try {
                ds.writeLong(System.currentTimeMillis());
            } catch (IOException ioe) {
            }
            e = bs.toByteArray();
        }
    }
}


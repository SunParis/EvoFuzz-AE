import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.io.ObjectOutputStream;

public class TplClass7345 {

    private static final void method(boolean err, byte[] data, java.util.Calendar cal) throws Throwable {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(cal);
            oos.flush();
            oos.close();
            data = baos.toByteArray();
        } catch (Exception e) {
            err = true;
        }
    }
}


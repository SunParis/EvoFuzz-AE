import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.io.ObjectOutputStream;

public class TplClass7347 {

    private static final void method(byte[] data, java.util.Calendar cal) throws Throwable {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(cal);
        oos.flush();
        oos.close();
        data = baos.toByteArray();
    }
}


import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.io.ObjectInputStream;

public class TplClass7348 {

    private static final void method(byte[] data, java.util.Calendar cal) throws Throwable {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        cal = (Calendar) ois.readObject();
        ois.close();
    }
}


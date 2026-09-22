import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.io.ObjectInputStream;

public class TplClass7346 {

    private static final void method(boolean err, byte[] data, java.util.Calendar cal) throws Throwable {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            cal = (Calendar) ois.readObject();
            ois.close();
        } catch (Exception e) {
            err = true;
        }
    }
}


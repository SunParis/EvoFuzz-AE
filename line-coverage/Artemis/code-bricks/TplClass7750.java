import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TplClass7750 {

    private static final void method() throws Throwable {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(Collections.EMPTY_LIST);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            if (!Collections.EMPTY_LIST.equals(in.readObject()))
                ;
        } catch (Exception e) {
        }
    }
}


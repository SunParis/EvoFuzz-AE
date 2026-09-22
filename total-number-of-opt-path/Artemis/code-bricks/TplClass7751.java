import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Set;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TplClass7751 {

    private static final void method() throws Throwable {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            Set gumby = Collections.singleton("gumby");
            out.writeObject(gumby);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            if (!gumby.equals(in.readObject()))
                ;
        } catch (Exception e) {
        }
    }
}


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Collections;
import java.io.ObjectInputStream;
import java.util.Set;
import java.io.ObjectOutputStream;

public class TplClass7753 {

    private static final void method() throws Throwable {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(Collections.EMPTY_SET);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            if (!Collections.EMPTY_SET.equals(in.readObject()))
                ;
        } catch (Exception e) {
        }
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
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            List gumbies = Collections.nCopies(50, "gumby");
            out.writeObject(gumbies);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            if (!gumbies.equals(in.readObject()))
                ;
        } catch (Exception e) {
        }
    }
}


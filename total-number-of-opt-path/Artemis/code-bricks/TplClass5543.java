import java.util.List;
import java.util.UUID;
import java.util.Random;
import java.util.LinkedList;

public class TplClass5543 {

    private static final void method() throws Throwable {
        Random byteSource = new Random();
        byte[] someBytes = new byte[12];
        List list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            byteSource.nextBytes(someBytes);
            UUID u1 = UUID.nameUUIDFromBytes(someBytes);
            if (3 != u1.version()) {
            }
            if (2 != u1.variant()) {
            }
            if (list.contains(u1))
                ;
            list.add(u1);
        }
    }
}


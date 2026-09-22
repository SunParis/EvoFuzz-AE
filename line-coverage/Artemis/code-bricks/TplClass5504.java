import java.util.List;
import java.util.UUID;

public class TplClass5504 {

    private static final void method(java.util.List list) throws Throwable {
        for (int i = 0; i < 100; i++) {
            UUID u1 = UUID.randomUUID();
            if (4 != u1.version()) {
            }
            if (2 != u1.variant()) {
            }
            if (list.contains(u1))
                ;
            list.add(u1);
        }
    }
}


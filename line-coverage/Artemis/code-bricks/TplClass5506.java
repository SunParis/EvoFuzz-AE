import java.util.UUID;

public class TplClass5506 {

    private static final void method() throws Throwable {
        for (int i = 0; i < 100; i++) {
            UUID u1 = UUID.randomUUID();
            UUID u2 = UUID.fromString(u1.toString());
            if (!u1.equals(u2))
                ;
        }
    }
}


import java.util.UUID;

public class TplClass5545 {

    private static final void method() throws Throwable {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.fromString(u1.toString());
        if (!u1.equals(u2))
            ;
    }
}


import java.util.UUID;
import java.util.Random;

public class TplClass5508 {

    private static final void method(java.util.Random generator) throws Throwable {
        // Test equality of UUIDs with tampered bits
        for (int i = 0; i < 1000; i++) {
            long l = generator.nextLong();
            long l2 = generator.nextLong();
            int position = generator.nextInt(64);
            UUID u1 = new UUID(l, l2);
            l = l ^ (1L << position);
            UUID u2 = new UUID(l, l2);
            if (u1.equals(u2))
                ;
        }
    }
}


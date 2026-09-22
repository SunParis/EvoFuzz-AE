import java.nio.ByteBuffer;

public class TplClass7287 {

    private static final void method(java.nio.ByteBuffer bb, java.lang.StringBuilder sb) throws Throwable {
        for (int i = 0; i < bb.limit(); i++) sb.append(String.format("%02x ", bb.get(i)));
    }
}


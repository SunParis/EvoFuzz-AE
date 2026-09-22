import java.nio.InvalidMarkException;
import java.nio.CharBuffer;

public class TplClass7654 {

    private static final void method(java.nio.CharBuffer slice, java.nio.CharBuffer buff) throws Throwable {
        boolean marked = false;
        try {
            slice.reset();
            marked = true;
        } catch (InvalidMarkException ime) {
            // expected
        }
        if (marked || slice.position() != 0 || buff.remaining() != slice.limit() || buff.remaining() != slice.capacity()) {
        }
    }
}


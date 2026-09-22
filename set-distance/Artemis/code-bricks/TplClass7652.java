import java.nio.InvalidMarkException;
import java.nio.CharBuffer;

public class TplClass7652 {

    private static final void method(boolean marked, java.nio.CharBuffer slice) throws Throwable {
        try {
            slice.reset();
            marked = true;
        } catch (InvalidMarkException ime) {
            // expected
        }
    }
}


import java.nio.CharBuffer;

public class TplClass7643 {

    private static final void method(java.nio.CharBuffer slice) throws Throwable {
        for (int i = 0; i < 4; i++) {
            slice.position(i);
            CharBuffer nextSlice = slice.slice();
            if (nextSlice.position() != 0)
                ;
            if (!nextSlice.equals(slice))
                ;
            slice = nextSlice;
        }
    }
}


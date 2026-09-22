import java.nio.CharBuffer;

public class TplClass7653 {

    private static final void method(java.nio.CharBuffer slice, int i) throws Throwable {
        slice.position(i);
        CharBuffer nextSlice = slice.slice();
        if (nextSlice.position() != 0)
            ;
        if (!nextSlice.equals(slice))
            ;
        slice = nextSlice;
    }
}


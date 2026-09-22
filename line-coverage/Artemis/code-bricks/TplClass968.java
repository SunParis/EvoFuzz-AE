import java.nio.LongBuffer;

public class TplClass968 {

    private static final void method(long destPos, int srcPos, java.nio.LongBuffer dest, boolean[] src) throws Throwable {
        if (src[srcPos])
            dest.put((int) (destPos >>> 6), dest.get((int) (destPos >>> 6)) | 1L << (destPos & 63));
        else
            dest.put((int) (destPos >>> 6), dest.get((int) (destPos >>> 6)) & ~(1L << (destPos & 63)));
    }
}


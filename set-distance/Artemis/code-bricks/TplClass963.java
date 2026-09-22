import java.nio.LongBuffer;

public class TplClass963 {

    private static final void method(int countFinish, long destPos, int srcPos, java.nio.LongBuffer dest, boolean[] src) throws Throwable {
        for (int srcPosMax = srcPos + countFinish; srcPos < srcPosMax; srcPos++, destPos++) {
            if (src[srcPos])
                dest.put((int) (destPos >>> 6), dest.get((int) (destPos >>> 6)) | 1L << (destPos & 63));
            else
                dest.put((int) (destPos >>> 6), dest.get((int) (destPos >>> 6)) & ~(1L << (destPos & 63)));
        }
    }
}


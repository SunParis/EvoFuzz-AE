public class TplClass1624 {

    private static final void method(long val, int i, byte[] inArr) throws Throwable {
        while (i < inArr.length) {
            val = (val << 8) + (inArr[i++] & 0xff);
        }
    }
}


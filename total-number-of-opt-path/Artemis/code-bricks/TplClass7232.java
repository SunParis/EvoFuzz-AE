import java.nio.ByteOrder;

public class TplClass7232 {

    private static final void method() throws Throwable {
        ByteOrder bo = ByteOrder.nativeOrder();
        String arch = System.getProperty("os.arch");
        if (((arch.equals("i386") && (bo != ByteOrder.LITTLE_ENDIAN))) || ((arch.equals("sparc") && (bo != ByteOrder.BIG_ENDIAN)))) {
        }
    }
}


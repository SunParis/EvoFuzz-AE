import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;

public class TplClass4360 {

    private static final void method() throws Throwable {
        ByteBuffer direct = ByteBuffer.allocateDirect(100);
        direct.order(ByteOrder.nativeOrder());
        IntBuffer int1 = direct.asIntBuffer();
        int[] data = new int[25];
        // float data[] = new float[25];
        int1.clear();
        int1.put(data);
        int1.position(0);
        int1.clear();
        int1.put(data);
        int1.position(0);
    }
}


import java.util.LinkedList;

public class TplClass2741 {

    private static final void method(int objSize, java.util.LinkedList<java.lang.Object> list, boolean sawEx) throws Throwable {
        try {
            for (int i = 0; i < 2048 / objSize; i++) {
                list.add((Object) new byte[objSize]);
            }
        } catch (OutOfMemoryError oom) {
            sawEx = true;
        }
    }
}


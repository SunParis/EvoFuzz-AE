import java.util.LinkedList;

public class TplClass2735 {

    private static final void method(java.util.LinkedList<java.lang.Object> list, int objSize) throws Throwable {
        for (int i = 0; i < 2048 / objSize; i++) {
            list.add((Object) new byte[objSize]);
        }
    }
}


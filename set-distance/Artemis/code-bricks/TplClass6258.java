import java.util.HashSet;

public class TplClass6258 {

    private static final void method() throws Throwable {
        // this should generate an IllegalArgumentException
        HashSet bad1 = new HashSet(100, -3);
    }
}


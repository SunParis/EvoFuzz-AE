import java.util.HashMap;

public class TplClass6257 {

    private static final void method() throws Throwable {
        // this should generate an IllegalArgumentException
        HashMap bad1 = new HashMap(100, Float.NaN);
    }
}


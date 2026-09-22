import java.util.Hashtable;

public class TplClass6255 {

    private static final void method() throws Throwable {
        // this should generate an IllegalArgumentException
        Hashtable bad1 = new Hashtable(100, Float.NaN);
    }
}


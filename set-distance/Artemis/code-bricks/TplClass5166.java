import java.util.Vector;

public class TplClass5166 {

    private static final void method() throws Throwable {
        // this should generate an IllegalArgumentException
        Vector bad1 = new Vector(-100, 10);
    }
}


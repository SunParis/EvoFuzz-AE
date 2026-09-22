import java.util.Vector;

public class TplClass5158 {

    private static final void method() throws Throwable {
        Vector v = new Vector(10);
        try {
            int i = v.lastIndexOf(null, 5);
        } catch (IndexOutOfBoundsException e) {
        }
    }
}


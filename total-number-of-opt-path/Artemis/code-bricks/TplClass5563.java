import java.util.HashMap;
import java.util.jar.Attributes.Name;
import java.util.jar.Attributes;

public class TplClass5563 {

    private static final void method() throws Throwable {
        Attributes at = new Attributes();
        try {
            at.put("this is not an Attributes.Name", "value");
        } catch (ClassCastException e) {
        }
        try {
            at.put(new Attributes.Name("name"), new Integer(0));
        } catch (ClassCastException e) {
        }
        try {
            at.putAll(new HashMap());
        } catch (ClassCastException e) {
        }
    }
}


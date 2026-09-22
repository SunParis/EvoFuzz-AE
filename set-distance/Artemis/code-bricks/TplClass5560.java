import java.util.jar.Attributes;

public class TplClass5560 {

    private static final void method(java.util.jar.Attributes at) throws Throwable {
        try {
            at.put("this is not an Attributes.Name", "value");
        } catch (ClassCastException e) {
        }
    }
}


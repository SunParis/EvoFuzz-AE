import java.util.Properties;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class TplClass5263 {

    private static final void method(java.util.Properties props) throws Throwable {
        // store as XML format
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        props.storeToXML(out, null, "UTF-8");
        // load from XML format
        Properties p = new Properties();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        p.loadFromXML(in);
        // check that the properties are as expected
        if (!p.equals(props))
            ;
    }
}


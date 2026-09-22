import java.util.SortedMap;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashSet;

public class TplClass2570 {

    private static final void method() throws Throwable {
        // These charsets must be provided; anything else is optional.
        List<String> standardCharsets = Arrays.asList("US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16");
        SortedMap<String, Charset> all = Charset.availableCharsets();
        Set<String> needed = new HashSet<String>(standardCharsets);
        for (Map.Entry<String, Charset> e : all.entrySet()) {
            String canonicalName = e.getKey();
            needed.remove(canonicalName);
        }
    }
}


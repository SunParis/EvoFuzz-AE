import java.util.Map;
import java.security.Security;
import java.util.HashMap;
import java.security.Provider;

public class TplClass6336 {

    private static final void method(java.lang.String emptyServAlgFilter, java.lang.String serviceAlgFilter) throws Throwable {
        try {
            Provider[] providers1 = Security.getProviders();
            Provider[] providers2 = Security.getProviders(serviceAlgFilter);
            Map<String, String> filter = new HashMap<String, String>();
            filter.put(serviceAlgFilter, "");
            Provider[] providers3 = Security.getProviders(filter);
            Provider[] emptyProv1 = Security.getProviders(emptyServAlgFilter);
            if (emptyProv1 != null) {
            }
            Map<String, String> emptyFilter = new HashMap<String, String>();
            emptyFilter.put(emptyServAlgFilter, "");
            Provider[] emptyProv2 = Security.getProviders(emptyFilter);
            if (emptyProv2 != null) {
            }
        } catch (ExceptionInInitializerError e) {
        }
    }
}


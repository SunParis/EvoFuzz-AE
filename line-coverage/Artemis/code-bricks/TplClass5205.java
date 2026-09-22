import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TplClass5205 {

    private static final void method(java.lang.String SIGNAL, boolean isSignalReceived, java.lang.Process processB) throws Throwable {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(processB.getInputStream(), "utf-8"))) {
            String result;
            while ((result = in.readLine()) != null) {
                if (SIGNAL.equals(result)) {
                    isSignalReceived = true;
                } else {
                }
            }
        }
    }
}


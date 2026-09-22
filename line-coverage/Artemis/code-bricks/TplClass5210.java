import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TplClass5210 {

    private static final void method(java.lang.String SIGNAL, java.lang.Process processB) throws Throwable {
        boolean isSignalReceived = false;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(processB.getInputStream(), "utf-8"))) {
            String result;
            while ((result = in.readLine()) != null) {
                if (SIGNAL.equals(result)) {
                    isSignalReceived = true;
                } else {
                }
            }
        }
        if (!isSignalReceived) {
        }
    }
}


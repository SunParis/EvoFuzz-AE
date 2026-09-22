import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class TplClass5200 {

    private static final void method(java.lang.String SIGNAL, java.io.File outB, boolean fileOut, java.lang.Process processB) throws Throwable {
        if (fileOut) {
            try {
                processB.waitFor();
            } catch (InterruptedException ex) {
            }
            if (!outB.delete()) {
            }
        } else {
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
}


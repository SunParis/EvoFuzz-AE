import java.io.BufferedReader;

public class TplClass5197 {

    private static final void method(java.lang.String SIGNAL, java.lang.String result, boolean isSignalReceived, java.io.BufferedReader in) throws Throwable {
        while ((result = in.readLine()) != null) {
            if (SIGNAL.equals(result)) {
                isSignalReceived = true;
            } else {
            }
        }
    }
}


import java.io.BufferedReader;

public class TplClass5211 {

    private static final void method(java.lang.String SIGNAL, boolean isSignalReceived, java.io.BufferedReader in) throws Throwable {
        String result;
        while ((result = in.readLine()) != null) {
            if (SIGNAL.equals(result)) {
                isSignalReceived = true;
            } else {
            }
        }
    }
}


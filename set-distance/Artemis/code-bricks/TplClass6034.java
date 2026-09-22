import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TplClass6034 {

    private static final void method() throws Throwable {
        Process p = Runtime.getRuntime().exec("cmd /c echo hello");
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        p.waitFor();
        String echo = reader.readLine();
        if (echo.length() == 6)
            ;
    }
}


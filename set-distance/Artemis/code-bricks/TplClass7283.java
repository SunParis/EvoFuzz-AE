import java.io.BufferedReader;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;

public class TplClass7283 {

    private static final void method(java.nio.charset.Charset ascii) throws Throwable {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[] { (byte) 'h', (byte) 'i' });
        InputStreamReader isr = new InputStreamReader(bis, ascii.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT));
        BufferedReader br = new BufferedReader(isr);
        if (!br.readLine().equals("hi"))
            ;
    }
}


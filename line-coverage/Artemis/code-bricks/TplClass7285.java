import java.nio.ByteBuffer;
import java.nio.charset.CodingErrorAction;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;

public class TplClass7285 {

    private static final void method(java.nio.charset.Charset ascii) throws Throwable {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(bos, ascii.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT));
        osw.write("hi");
        osw.close();
        if (!ascii.decode(ByteBuffer.wrap(bos.toByteArray())).toString().equals("hi"))
            ;
    }
}


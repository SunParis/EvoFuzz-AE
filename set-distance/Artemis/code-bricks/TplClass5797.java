import java.nio.channels.Channels;
import java.io.Reader;
import java.nio.channels.ReadableByteChannel;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

public class TplClass5797 {

    private static final void method(java.nio.charset.Charset cs, int sz) throws Throwable {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[100]);
        ReadableByteChannel ch = Channels.newChannel(bis);
        Reader r = Channels.newReader(ch, cs.newDecoder(), sz);
        char[] arr = new char[100];
    }
}


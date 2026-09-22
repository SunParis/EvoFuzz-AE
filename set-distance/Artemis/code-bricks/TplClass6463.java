import java.io.FileOutputStream;

public class TplClass6463 {

    private static final void method(byte[] data) throws Throwable {
        FileOutputStream fos = new FileOutputStream("stored.zip");
        fos.write(data, 0, data.length);
        fos.close();
    }
}


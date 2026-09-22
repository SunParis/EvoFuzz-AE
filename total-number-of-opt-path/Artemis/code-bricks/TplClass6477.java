import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.DeflaterOutputStream;

public class TplClass6477 {

    private static final void method() throws Throwable {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater def = new Deflater();
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[10]);
        Inflater inf = new Inflater();
        InflaterInputStream infOS;
        DeflaterOutputStream defOS;
        try {
            defOS = new DeflaterOutputStream(bos, null);
        } catch (NullPointerException e) {
        }
        try {
            defOS = new DeflaterOutputStream(null, def);
        } catch (NullPointerException e) {
        }
        try {
            defOS = new DeflaterOutputStream(bos, def, -1);
        } catch (IllegalArgumentException e) {
        }
        try {
            infOS = new InflaterInputStream(bis, null);
        } catch (NullPointerException e) {
        }
        try {
            infOS = new InflaterInputStream(null, inf);
        } catch (NullPointerException e) {
        }
        try {
            infOS = new InflaterInputStream(bis, inf, -1);
        } catch (IllegalArgumentException e) {
        }
    }
}

